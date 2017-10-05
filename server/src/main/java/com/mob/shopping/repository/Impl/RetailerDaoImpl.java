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

import com.mob.shopping.constants.enums.RegistrationStatus;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.entity.Retailer;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.RetailerDao;
import com.mob.shopping.util.Constants;

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


	@SuppressWarnings("unchecked")
	@Override
	public List<Retailer> get(Long distributorId) throws BaseApplicationException {
		Session session = sessionFactory.getCurrentSession();

		 return  session.createCriteria(Retailer.class)
				 .add(Restrictions.eq(Constants.RETAILER.DISTRIBUTOR_ID,distributorId))
				 .add(Restrictions.eq(Constants.IS_DELETED,0)).list();
	}



	@Override
	public Retailer getPendingRetailerById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Retailer) session.createCriteria(Retailer.class)
		 .add(Restrictions.eq(Constants.ID,id))
		 .add(Restrictions.eq(Constants.RETAILER.REGISTRATION_STATUS,RegistrationStatus.PENDING.getValue()))
		 .add(Restrictions.eq(Constants.IS_DELETED,0)).uniqueResult();
	}


	@Override
	public void update(Retailer retailer) {
		String method = "[DAO] update>>>> Retailer :: "+retailer.toString();
    	logger.info(method);
            Session session = sessionFactory.getCurrentSession();
             session.update(retailer);

	}

    @Override
    public Retailer findByMsisdnAndRegistrationStatus(String msisdn, Integer registrationStatus) throws DaoException{
        String method = "[DAO] FETCH>>>> Retailer :: ";
        logger.info(method);
        Session session = sessionFactory.getCurrentSession();
       @SuppressWarnings("unchecked")
	List<Retailer> retailers = session.createCriteria(Retailer.class)
               .add(Restrictions.eq(Constants.MSISDN,msisdn))
               .add(Restrictions.eq("registrationStatus",registrationStatus))
           	  .add(Restrictions.eq(Constants.IS_DELETED,0))
               .list();
        if(retailers!=null && retailers.size()>0){
            return retailers.get(0);
        }
        return null;
    }

	@Override
	public boolean checkMsisdnStatus(String msisdn) throws DaoException {
		String method = "[DAO] FETCH>>>> Retailer :: ";
		logger.info(method);
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Retailer> retailers = session.createCriteria(Retailer.class)
				.add(Restrictions.eq(Constants.MSISDN,msisdn))
				.add(Restrictions.eq(Constants.IS_DELETED,0))
				.add(Restrictions.le("registrationStatus",1))
				.list();
		if(retailers!=null && retailers.size()>0){
			retailers.forEach(retailer -> {
				if(retailer.getRegistrationStatus() ==
                        RegistrationStatus.APPROVED.getValue()){
				    throw new DaoException(ResponseCode.RETAILER_ALREADY_REGISTER);
                }
			});
            throw new DaoException(ResponseCode.RETAILER_REQUEST_PENDING);
		}
		return true;	}
}
