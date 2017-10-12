package com.mob.shopping.service;


import java.util.List;

import com.mob.shopping.beans.request.DistributorDto;
import com.mob.shopping.entity.Distributor;
import com.mob.shopping.exception.BaseApplicationException;

public interface DistributorServices {
	List<DistributorDto> getDistributors(Long distributorListRequest) throws BaseApplicationException;
	Distributor getById(Long distributorId) throws BaseApplicationException;
	void update(Distributor distributor) throws BaseApplicationException;

}
