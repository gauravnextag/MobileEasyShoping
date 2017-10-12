package com.mob.shopping.repository.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mob.shopping.beans.request.DistributorDto;
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
	public List<DistributorDto> getDistributors(Long districtId) throws DaoException {
		String method = "[DAO] getDistributors>>>> districtId :: " + districtId;
		logger.info(method);
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Query query = session
				.createSQLQuery("select id,name,msisdn,srd_md_name as srdMdName from"
						+ " DISTRIBUTOR where DISTRICT_ID =:districtId and IS_DELETED =:isDeleted order by RETAILER_COUNT")
				.addScalar("id", StandardBasicTypes.LONG).addScalar("name", StandardBasicTypes.STRING)
				.addScalar("msisdn", StandardBasicTypes.STRING)
				.addScalar("srdMdName", StandardBasicTypes.STRING)
				.setParameter(Constants.DISTRIBUTOR.DISTRICT_ID, districtId).setParameter(Constants.IS_DELETED, 0);

		query.setResultTransformer(Transformers.aliasToBean(DistributorDto.class));
		return query.list();
		// return session.createCriteria(Distributor.class)
		// .add(Restrictions.eq(Constants.DISTRIBUTOR.DISTRICT_ID, districtId))
		// .add(Restrictions.eq(Constants.IS_DELETED,0))
		// .addOrder(Order.asc(Constants.DISTRIBUTOR.RETAILER_COUNT))
		// .list();

	}

	@Override
	public Distributor findByMsisdn(String msisdn) throws DaoException {
		String method = "[DAO] FETCH>>>> Distributor :: ";
		logger.info(method);
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Distributor> distributors = session.createCriteria(Distributor.class)
				.add(Restrictions.eq(Constants.MSISDN, msisdn)).add(Restrictions.eq(Constants.IS_DELETED, 0)).list();
		if (distributors != null && distributors.size() > 0) {
			return distributors.get(0);
		}
		return null;
	}

	@Override
	public void save(Distributor distributor) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		session.save(distributor);
	}

	@Override
	public Distributor getById(Long distributorId) throws DaoException {
		String method = "[DAO] getDistributors>>>> distributorId :: " + distributorId;
		logger.info(method);
		Session session = sessionFactory.getCurrentSession();
		return (Distributor) session.createCriteria(Distributor.class).add(Restrictions.eq(Constants.ID, distributorId))
				.add(Restrictions.eq(Constants.IS_DELETED, 0)).uniqueResult();

	}

}
