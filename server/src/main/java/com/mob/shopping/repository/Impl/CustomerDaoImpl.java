package com.mob.shopping.repository.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mob.shopping.entity.Customer;
import com.mob.shopping.repository.CustomerDao;
import com.mob.shopping.util.Constants;

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


	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> get(Long retailerId) {
		String method = "[DAO] get>>>> retailerId :: "+retailerId;
    	logger.info(method);
            Session session = sessionFactory.getCurrentSession();
            return session.createCriteria(Customer.class)
            		.add(Restrictions.eq(Constants.Customer.RETAILER_ID,retailerId))
            				.add(Restrictions.eq(Constants.IS_DELETED,0)).list();
	}

}
