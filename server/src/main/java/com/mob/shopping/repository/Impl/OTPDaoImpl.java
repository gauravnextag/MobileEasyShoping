package com.mob.shopping.repository.Impl;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.constants.ConfigConstants;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.entity.OTP;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.OTPDao;
import com.mob.shopping.service.MasterConfigService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public class OTPDaoImpl implements OTPDao {
    private static final Logger logger = LoggerFactory.getLogger(OTPDaoImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MasterConfigService masterConfigService;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackOn = Exception.class)
    public OTPOperationDTO verifyOTP(String msisdn, String otp) {

        OTPOperationDTO otpOperationDTO = null;

        final Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        try {
            final int maxTimeAllowed = Integer.parseInt(masterConfigService.getValueByKey(ConfigConstants
                    .MAX_OTP_INTERVAL));
            currentTimestamp.setTime(currentTimestamp.getTime()+maxTimeAllowed*60*1000);
            final Timestamp expiryTimestamp = currentTimestamp;
            List<OTP> otpList;
            Session session = sessionFactory.getCurrentSession();
            logger.info("time:{}",expiryTimestamp);
            otpList = session.createCriteria(OTP.class).add(Restrictions.eq("msisdn", msisdn)).add(Restrictions.gt("lastModifiedDate", expiryTimestamp)).list();

            if (null != otpList && otpList.size() > 0) {
                final OTP otpInfo = otpList.get(0);

                if (otpInfo.getOpt().equals(otp)) {
                logger.info("OTP verification successful for user {}", msisdn);
                session.delete(otpInfo);
                otpOperationDTO = new OTPOperationDTO(ErrorConstants.OTP_VERIFICATION_SUCCESS);
                } else {
                    logger.info("DSL OTP verification failed for user {}, updating failed attempts", msisdn);
                    otpInfo.setAttempts(otpInfo.getAttempts() + 1);
                    session.saveOrUpdate(otpInfo);
                    otpOperationDTO = new OTPOperationDTO(ErrorConstants.ERROR_OTP_VERIFICATION_FAILED);
                }
            } else {
                logger.info("OTP list is empty user {}", msisdn);
                otpOperationDTO = new OTPOperationDTO(ErrorConstants.ERROR_INVALID_OTP_TOKEN);
            }
        } catch (Exception exception) {
            logger.error("Exception occurred while validating Distibutor Info ", exception);
            throw new DaoException(ResponseCode.ERROR_MESSAGE_DAO);

        }
        return otpOperationDTO;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OTP generateOTP(String msisdn, String otpCode, String uuid, Long id, Long oldAttempts) throws DaoException {

        Session session = null;
        final OTP otp = new OTP();
        otp.setMsisdn(msisdn);
        otp.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        otp.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
        otp.setAttempts(oldAttempts + 1);
        otp.setOpt(otpCode);

        try {
            session = sessionFactory.getCurrentSession();
            if (oldAttempts > 0) {
                otp.setId(id);
                session.update(otp);
            }
            session.save(otp);
        } catch (Exception exception) {
            logger.error("Exception occurred while saving OTP Info ", exception);
            throw new DaoException(ResponseCode.ERROR_MESSAGE_DAO);
        }
        return otp;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OTP findByMsisdn(String msisdn) {
        Session session = null;
        final Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        try {
            session = sessionFactory.getCurrentSession();
            final int maxTimeAllowed = Integer.parseInt(masterConfigService.getValueByKey(ConfigConstants.MAX_OTP_INTERVAL));
            currentTimestamp.setTime(currentTimestamp.getTime()+maxTimeAllowed*60*1000);
            final Timestamp expiryTimestamp = currentTimestamp;

            List<OTP> otpList = session.createCriteria(OTP.class).add(Restrictions.eq("msisdn", msisdn)).list();
            if(otpList != null){
                if (otpList.size() > 0 && otpList.get(0).getLastModifiedDate().getTime() >
                        expiryTimestamp.getTime()) {
                    return otpList.get(0);
                }else {
                    session.delete(otpList.get(0));
                }
            }
        } catch (Exception e) {
            logger.error("Exception occurred while fetching OTP for msisdn ", msisdn);
            throw new DaoException(ResponseCode.ERROR_MESSAGE_DAO);
        }
        return null;
    }
}