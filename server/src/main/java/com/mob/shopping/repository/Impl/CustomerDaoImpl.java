package com.mob.shopping.repository.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mob.shopping.entity.Customer;
import com.mob.shopping.repository.CustomerDao;

@Transactional
@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

	
	@Override
	public Long save(Customer customer){
		String method = "[DAO] save>>>> Customer :: "+customer.toString();
    	logger.info(method);
            Session session = sessionFactory.getCurrentSession();
            session.persist(customer);
            return customer.getId();
	}

}
