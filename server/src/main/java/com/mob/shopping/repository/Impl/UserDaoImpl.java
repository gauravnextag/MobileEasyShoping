/**
 * @Ajay Mishra
 */
package com.mob.shopping.repository.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mob.shopping.entity.User;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.UserDao;
import com.mob.shopping.util.Constants;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByMSISDN(String msisdn) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.createCriteria(User.class).add(Restrictions.eq(Constants.MSISDN, msisdn))
				.add(Restrictions.eq(Constants.IS_DELETED, 0)).uniqueResult();
	}

	@Override
	public User findByUserIdAndMSISDN(Long userId, String msisdn) throws DaoException {
		Session session = sessionFactory.getCurrentSession();

		return (User) session.createCriteria(User.class).add(Restrictions.eq(Constants.MSISDN, msisdn))
				.add(Restrictions.eq(Constants.USER_ID, userId)).add(Restrictions.eq(Constants.IS_DELETED, 0))
				.uniqueResult();
	}

	@Override
	public void save(User user) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

}
