package com.mob.shopping.service;

import com.mob.shopping.exception.BusinessException;


public interface MasterConfigService {

    String getValueByKey(String key) throws BusinessException;
    String getValueForSystemKey(String key) throws BusinessException;
}
