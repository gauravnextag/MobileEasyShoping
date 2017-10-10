package com.mob.shopping.repository;

import java.util.List;

import com.mob.shopping.entity.District;
import com.mob.shopping.entity.State;
import com.mob.shopping.exception.DaoException;

public interface RegionDao {
   public List<State> getStates() throws DaoException;
   public State findOrSaveState(State state) throws DaoException;
   public List<District> getDistricts(Long stateId) throws DaoException;
   public District findOrSaveDistrict(District district) throws DaoException;

}
