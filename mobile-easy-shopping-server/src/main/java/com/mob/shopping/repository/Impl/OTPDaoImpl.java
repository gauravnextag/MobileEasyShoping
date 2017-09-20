package com.mob.shopping.repository.Impl;

import com.mob.shopping.constants.ConfigConstants;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.entity.OTP;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.OTPDao;
import com.mob.shopping.service.MasterConfigService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
@Transactional
public class OTPDaoImpl implements OTPDao {
    private static final Logger logger = LoggerFactory.getLogger(OTPDaoImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private MasterConfigService masterConfigService;

//    @SuppressWarnings("unchecked")
//    @Override
//    @Transactional(rollbackOn = Exception.class)
//    public OTPOperationDTO verifyOTP(String msisdn, String otp, String otpToken) {
//
//        OTPOperationDTO otpOperationDTO = null;
//
//        try {
//            final Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
//            final Calendar currentDateTime = Calendar.getInstance();
//            currentDateTime.setTime(currentTimestamp);
//
//            final int maxTimeAllowed = Integer.parseInt(mastersDAO.getMasterConfig(MasterConfigKeys.MAX_OTP_INTERVAL).getValue());
//
//            currentDateTime.add(Calendar.MINUTE, maxTimeAllowed * -60);
//            final Timestamp expiryTimestamp = new Timestamp(currentDateTime.getTime().getTime());
//
//            List<DSLOTP> otpList = new ArrayList<DSLOTP>();
//
//            Session session = sessionFactory.getCurrentSession();
//
//            Query query = session.createQuery("from DSLOTP where msisdn = :msisdn and generatedOn > :generatedOn and otpToken = :otpToken order by generatedOn desc");
//            query.setParameter("msisdn", msisdn);
//            query.setParameter("generatedOn", expiryTimestamp);
//            query.setParameter("otpToken", otpToken);
//
//            otpList = query.list();
//
//            if (null != otpList && otpList.size() > 0) {
//                final DSLOTP otpInfo = otpList.get(0);
//
//                if (otpInfo.getAttempts().intValue() >= Integer.parseInt(mastersDAO.getMasterConfig(MasterConfigKeys.MAX_OTP_ATTEMPT).getValue())) {
//                    logger.info("Verify otp attempts exhausted for user {}", msisdn);
//                    otpOperationDTO = new OTPOperationDTO(CAFConstants.ERROR_ATTEMPTS_EXHAUSTED);
//
//                } else if (otpInfo.getOtpCode().equals(otp)) {
//                    logger.info("OTP verification successful for user {}", msisdn);
//                    session.delete(otpInfo);
//                    otpOperationDTO = new OTPOperationDTO(CAFConstants.OTP_VERIFICATION_SUCCESS);
//                } else {
//                    logger.info("DSL OTP verification failed for user {}, updating failed attempts", msisdn);
//                    otpInfo.setAttempts(otpInfo.getAttempts() + 1);
//                    session.saveOrUpdate(otpInfo);
//                    otpOperationDTO = new OTPOperationDTO(CAFConstants.ERROR_OTP_VERIFICATION_FAILED);
//                }
//            } else {
//                logger.info( "DSL OTP list is empty for otpToken {} and user {}", otpToken, msisdn);
//                otpOperationDTO = new OTPOperationDTO(CAFConstants.ERROR_INVALID_OTP_TOKEN);
//            }
//        } catch (Exception exception) {
//            logger.error("Exception occurred while validating Distibutor Info ", exception);
//        }
//        return otpOperationDTO;
//    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OTP generateOTP(String msisdn, String otpCode, String uuid) throws DaoException {

        Session session = null;
        final OTP otp = new OTP();
        otp.setMsisdn(msisdn);
        otp.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        otp.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
        otp.setAttempts(Long.valueOf(0));
        otp.setOpt(otpCode);

        try {
            session = sessionFactory.getCurrentSession();
            session.save(otp);
        } catch (Exception exception) {
            logger.error("Exception occurred while saving OTP Info ", exception);
            throw new DaoException(ErrorConstants.ERROR_CODE, ErrorConstants.ERROR_MESSAGE_DAO, exception);
        }
        return otp;
    }



    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean areOTPGenerationExhausted(String msisdn) {

        final Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        final Calendar currentDateTime = Calendar.getInstance();
        currentDateTime.setTime(currentTimestamp);

        List<OTP> otpList = new ArrayList<OTP>();
        try {

            final int maxTimeAllowed = Integer.parseInt(masterConfigService.getValueByKey(ConfigConstants.MAX_OTP_INTERVAL));

            currentDateTime.add(Calendar.MINUTE, maxTimeAllowed * -60);
            final Timestamp expiryTimestamp = new Timestamp(currentDateTime.getTime().getTime());

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("from OTP where msisdn=:msisdn and modified_date > :generatedOn");
            query.setParameter("msisdn", msisdn);
            query.setParameter("generatedOn", expiryTimestamp);

            otpList = query.list();

            if (null != otpList && otpList.size() > 0) {
                if (otpList.size() >= Integer.parseInt(masterConfigService.getValueByKey(ConfigConstants.MAX_OTP_ATTEMPT))) {
                    logger.info("areOTPGenerationExhausted is TRUE >> otpList.size()" + otpList.size()
                            + ", maxOTPAttempts::" + masterConfigService.getValueByKey(ConfigConstants.MAX_OTP_ATTEMPT));
                    return true;
                }
            }
        } catch (Exception exception) {
            logger.error("Exception occurred while validating OTP attempts ", exception);
        }
        return false;
    }

}
