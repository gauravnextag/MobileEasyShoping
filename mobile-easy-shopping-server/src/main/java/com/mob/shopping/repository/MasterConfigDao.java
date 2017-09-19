package com.mob.shopping.repository;

import com.mob.shopping.entity.ConfigMaster;
import com.mob.shopping.exception.DaoException;

import java.util.List;

public interface MasterConfigDao {

    String findByKey(String key) throws DaoException;

    public List<ConfigMaster> getAllConfigData() throws DaoException;

}
