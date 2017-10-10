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
import com.mob.shopping.util.Constants;

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
            return  session.createCriteria(District.class).add(Restrictions.eq(Constants.STATE_ID, stateId))
            		.add(Restrictions.eq(Constants.IS_DELETED,0)).list();
    }

	@Override
	public State findOrSaveState(State state) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        State stateObj  = (State) session.createCriteria(State.class).add(Restrictions.eq(Constants.NAME, state.getName()))
		.add(Restrictions.eq(Constants.IS_DELETED,0)).uniqueResult();
    
        if(stateObj==null){
        	  session.persist(state);
        	  stateObj  = (State) session.createCriteria(State.class).add(Restrictions.eq(Constants.NAME, state.getName()))
        	    		.add(Restrictions.eq(Constants.IS_DELETED,0)).uniqueResult();
        }
        return stateObj;
       
	
	}

	
	@Override
	public District findOrSaveDistrict(District district) throws DaoException {
	    Session session = sessionFactory.getCurrentSession();
	    District districtObj  = (District) session.createCriteria(District.class).add(Restrictions.eq(Constants.NAME, district.getName()))
		.add(Restrictions.eq(Constants.IS_DELETED,0)).uniqueResult();
    
        if(districtObj==null){
        	  session.persist(district);
        	  districtObj  = (District) session.createCriteria(District.class).add(Restrictions.eq(Constants.NAME, district.getName()))
        	    		.add(Restrictions.eq(Constants.IS_DELETED,0)).uniqueResult();
        }
        return districtObj;
       
	}

}
