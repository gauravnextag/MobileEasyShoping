package com.mob.shopping.repository.Impl;

import com.mob.shopping.repository.RetailerDao;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CustomerDaoImpl implements RetailerDao {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);

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
