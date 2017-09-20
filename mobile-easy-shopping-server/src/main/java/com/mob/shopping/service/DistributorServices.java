package com.mob.shopping.service;


import com.mob.shopping.beans.request.DistributorListRequest;
import com.mob.shopping.beans.request.DistrictRequest;
import com.mob.shopping.entity.Distributor;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.exception.BusinessException;

import java.util.List;

public interface DistributorServices {
	List<Distributor> getDistributors(DistributorListRequest distributorListRequest) throws BaseApplicationException;

}
