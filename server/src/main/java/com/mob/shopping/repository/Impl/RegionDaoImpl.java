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

import com.mob.shopping.entity.District;
import com.mob.shopping.entity.State;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.RegionDao;

@Transactional
@Repository
public class RegionDaoImpl implements RegionDao {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionDaoImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<State> getStates() throws DaoException {
              Session session = sessionFactory.getCurrentSession();
            return session.createCriteria(State.class).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<District> getDistricts(Long stateId) throws DaoException {
            LOGGER.info("Fetching list of district corresponding to state id: {}",stateId);
            Session session = sessionFactory.getCurrentSession();
            return  session.createCriteria(District.class).add(Restrictions.eq("stateId", stateId)).list();
    }

}
