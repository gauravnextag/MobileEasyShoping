package com.mob.shopping.repository;

import java.util.List;

import com.mob.shopping.entity.Distributor;
import com.mob.shopping.exception.DaoException;

public interface DistributorDao {

	List<Distributor> getDistributors(Long districtId) throws DaoException;

	Distributor getById(Long distributorId) throws DaoException;

	Distributor findByMsisdn(String msisdn) throws DaoException;

	void save(Distributor distributor) throws DaoException;
}
