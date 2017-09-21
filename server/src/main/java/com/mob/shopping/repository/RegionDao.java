package com.mob.shopping.repository;

import java.util.List;

import com.mob.shopping.entity.District;
import com.mob.shopping.entity.State;
import com.mob.shopping.exception.DaoException;

public interface RegionDao {
   public List<State> getStates() throws DaoException;
   public List<District> getDistricts(String stateId) throws DaoException;
}