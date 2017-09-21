package com.mob.shopping.repository.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mob.shopping.entity.Retailer;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.RetailerDao;

@Transactional
@Repository
public class RetailerDaoImpl implements RetailerDao {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(RetailerDaoImpl.class);

    @Override
    public void save(Retailer retailer) throws DaoException{
    	String method = "[DAO] save>>>> Retailer :: "+retailer.toString();
    	logger.info(method);
            Session session = sessionFactory.getCurrentSession();
            session.persist(retailer);
    }
}
