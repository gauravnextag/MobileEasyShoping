package com.mob.shopping.repository.Impl;

import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.entity.District;
import com.mob.shopping.entity.State;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.RegionDao;
import com.mob.shopping.repository.RetailerDao;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class RegionDaoImpl implements RegionDao {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionDaoImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<State> getStates() throws DaoException {
        try {
            LOGGER.info("Fetching list of states from db");
            Session session = sessionFactory.getCurrentSession();
            return session.createCriteria(State.class).list();
        }catch (Exception e){
            LOGGER.error("Error while Fetching list of states from db");
            throw new DaoException(ErrorConstants.DAO_STATUS_CODE, ErrorConstants.ERROR_MESSAGE_DB_ERROR);

        }
    }

    @Override
    public List getDistricts(String stateId) throws DaoException {
        try {
            LOGGER.info("Fetching list of district corresponding to state id: {}",stateId);
            Session session = sessionFactory.getCurrentSession();
            return session.createCriteria(District.class).add(Restrictions.eq("stateId", stateId)).list();
        }catch (Exception e){
            LOGGER.error("Fetching list of district corresponding to state id: {}",stateId);
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
