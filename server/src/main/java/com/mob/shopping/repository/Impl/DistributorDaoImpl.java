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

import com.mob.shopping.entity.Distributor;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.DistributorDao;
import com.mob.shopping.util.Constants;

@Transactional
@Repository
public class DistributorDaoImpl implements DistributorDao {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(DistributorDaoImpl.class);
    @SuppressWarnings("unchecked")
	@Override
    public List<Distributor> getDistributors(Long districtId) throws DaoException {
    	String method = "[DAO] getDistributors>>>> districtId :: "+districtId;
    	logger.info(method);
            Session session = sessionFactory.getCurrentSession();
            return session.createCriteria(Distributor.class)
            		.add(Restrictions.eq(Constants.DISTRIBUTOR.DISTRICT_ID, districtId))
            		.add(Restrictions.eq(Constants.IS_DELETED,0))
            		.list();

    }

    @Override
    public Distributor findByMsisdn(String msisdn) throws DaoException {
        String method = "[DAO] FETCH>>>> Distributor :: ";
        logger.info(method);
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
		List<Distributor> distributors = session.createCriteria(Distributor.class)
		.add(Restrictions.eq(Constants.MSISDN,msisdn))
		.add(Restrictions.eq(Constants.IS_DELETED,0))
        .list();
        if(distributors!=null && distributors.size()>0){
            return distributors.get(0);
        }
        return null;
    }


	@Override
	public void save(Distributor distributor) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(distributor);
	}


}
