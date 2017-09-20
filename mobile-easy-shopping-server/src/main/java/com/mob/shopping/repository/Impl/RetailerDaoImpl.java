package com.mob.shopping.repository.Impl;

import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.constants.enums.RetailerRegistrationStatus;
import com.mob.shopping.entity.District;
import com.mob.shopping.entity.Retailer;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.RetailerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Transactional
@Repository
public class RetailerDaoImpl implements RetailerDao {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(RetailerDaoImpl.class);

    @Override
    public void save(RegistrationRequest registrationRequest) throws DaoException{
        Retailer retailer = new Retailer();
        try {
            retailer.setDistrictId(registrationRequest.getDistrictId());
            retailer.setDistributorID(registrationRequest.getDistributorId());
            retailer.setStore_name(registrationRequest.getStoreName());
            retailer.setMsisdn(registrationRequest.getMsisdn());
            retailer.setRegistrationStatus(RetailerRegistrationStatus.PENDING.getValue());
            retailer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            retailer.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
            LOGGER.info("Saving retailer details :{} into db",retailer.toString());
            Session session = sessionFactory.getCurrentSession();
            session.persist(retailer);
        }catch (Exception e){
            LOGGER.error("Error while Saving retailer details :{} into db: {}",retailer,e);
            throw new DaoException(ErrorConstants.DAO_STATUS_CODE, ErrorConstants.ERROR_MESSAGE_DB_ERROR);

        }
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public CheckBankStatusResponse findByMsisdnAndCafAndAppName(String msisdn, String caf, String appName) throws DaoException {
//        try {
//            Session session = sessionFactory.getCurrentSession();
//            List<Retailer> result = session.createCriteria(Retailer.class).add(Restrictions.eq("msisdn", msisdn)).add(Restrictions.eq("caf",caf)).add(Restrictions.eq("appName",appName)).list();
//            CheckBankStatusResponse checkBankStatusResponse = new CheckBankStatusResponse();
//            if (CollectionUtils.isNotEmpty(result)) {
//                checkBankStatusResponse.setCode(result.get(0).getStatus());
//                checkBankStatusResponse.setMessage(result.get(0).getMessage());
//            } else {
//                checkBankStatusResponse.setCode(CheckAccountStatus.RETRY.getValue());
//                checkBankStatusResponse.setMessage(CheckAccountStatus.RETRY_MESSAGE.getValue());
//            }
//            return checkBankStatusResponse;
//        } catch (Exception e) {
//            LOGGER.error("Inside BankTxnLogDaoImpl.findByMsisdnAndCafAndAppName for appName :{}, mobile number : {} and Caf Number {} : {},error {}", msisdn,caf,appName,e.getMessage());
//            throw new DaoException(ErrorConstants.DAO_STATUS_CODE, ErrorConstants.ERROR_MESSAGE_DB_ERROR);
//        }
//    }
//
//    @Override
//    public void save(Retailer bankTxnLog) throws DaoException{
//        try {
//            Session session = sessionFactory.getCurrentSession();
//            session.save(bankTxnLog);
//        }catch (Exception e){
//            LOGGER.error("Error while saving for bean {} is {}",bankTxnLog.toString() ,e.getMessage());
//            throw new DaoException(ErrorConstants.DAO_STATUS_CODE, ErrorConstants.ERROR_MESSAGE_DB_SAVE_ERROR);
//        }
//
//    }
}
