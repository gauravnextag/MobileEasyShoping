package com.mob.shopping.service;


import com.mob.shopping.beans.request.DistrictRequest;
import com.mob.shopping.exception.BusinessException;

import java.util.List;

public interface RegionServices {

    public List getStates() throws BusinessException;

    public List getDistrictRequest(DistrictRequest districtRequest) throws BusinessException;
}
