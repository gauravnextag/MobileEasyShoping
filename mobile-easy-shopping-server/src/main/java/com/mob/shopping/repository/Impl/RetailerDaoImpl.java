package com.mob.shopping.repository.Impl;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.constants.enums.RetailerRegistrationStatus;
import com.mob.shopping.entity.Retailer;
import com.mob.shopping.enums.ResponseCode;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.RetailerDao;

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
            throw new DaoException(ResponseCode.GENRAL_ERROR);

        }
    }
}
