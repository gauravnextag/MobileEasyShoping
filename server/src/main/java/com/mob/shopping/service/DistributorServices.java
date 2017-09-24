package com.mob.shopping.service;


import com.mob.shopping.entity.Distributor;
import com.mob.shopping.exception.BaseApplicationException;

import java.util.List;

public interface DistributorServices {
	List<Distributor> getDistributors(Long distributorListRequest) throws BaseApplicationException;

}
