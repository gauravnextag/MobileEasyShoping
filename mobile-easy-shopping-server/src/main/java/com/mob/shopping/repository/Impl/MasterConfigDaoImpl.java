package com.mob.shopping.repository.Impl;

import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.entity.ConfigMaster;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.MasterConfigDao;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class MasterConfigDaoImpl implements MasterConfigDao {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterConfigDaoImpl.class);

    @Override
    public String findByKey(String key) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<ConfigMaster> result = session.createCriteria(ConfigMaster.class).add(Restrictions.eq("key", key)).list();
            if (CollectionUtils.isNotEmpty(result)) {
                return result.get(0).getValue();
            } else {
                throw new DaoException(ErrorConstants.DAO_STATUS_CODE, ErrorConstants.ERROR_MESSAGE_NOT_FOUND_IN_DB);
            }
        } catch (Exception e) {
            LOGGER.error("Inside BankMasterConfigDaoImpl:fetching Value for key : {},error {}", key, e.getMessage());
            throw new DaoException(ErrorConstants.DAO_STATUS_CODE, ErrorConstants.ERROR_MESSAGE_DB_ERROR);
        }
    }

    @Override
    public List<ConfigMaster> getAllConfigData() throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<ConfigMaster> result = session.createCriteria(ConfigMaster.class).list();
            return result;
        } catch (Exception e) {
            LOGGER.error("Inside BankMasterConfigDaoImpl:fetching Value,error {}", e.getMessage());
            throw new DaoException(ErrorConstants.DAO_STATUS_CODE, ErrorConstants.ERROR_MESSAGE_DB_ERROR);
        }
    }


}
