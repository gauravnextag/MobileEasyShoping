package com.mob.shopping.service;


import java.util.List;

import com.mob.shopping.beans.request.DistrictRequest;
import com.mob.shopping.entity.District;
import com.mob.shopping.entity.State;
import com.mob.shopping.exception.BaseApplicationException;

public interface RegionServices {

    public List<State> getStates() throws BaseApplicationException;

    public List<District> getDistrictRequest(Long stateId) throws BaseApplicationException;
}
