package com.mob.shopping.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.MasterConfigDao;
import com.mob.shopping.service.MasterConfigService;

@Service
public class MasterConfigServiceImpl implements MasterConfigService {

    @Autowired

    MasterConfigDao MasterConfigDao;


    private static final Logger logger = LoggerFactory.getLogger(MasterConfigServiceImpl.class);

    @Override
    public String getValueByKey(String key) throws BusinessException {
        if (StringUtils.isEmpty(key)) {
        	new BusinessException(ResponseCode.GENRAL_ERROR);
            //throw new BusinessException(ErrorConstants.ERROR_CODE, ErrorConstants.ERROR_MESSAGE_CONIFG_KEY);
        }
        try {
            String value = MasterConfigDao.findByKey(key);
            return value;
        } catch (DaoException e) {
            logger.error("unable to get value for the given key: {}", key);
            new BusinessException(ResponseCode.GENRAL_ERROR);
            //throw new BusinessException(e.getError().getErrorCode(), e.getError().getErrorMessage());
        }
		return key;
    }

    @Override
    public String getValueForSystemKey(String key) throws BusinessException {
        if (StringUtils.isEmpty(key)) {
        	new BusinessException(ResponseCode.GENRAL_ERROR);
           // throw new BusinessException(ErrorConstants.ERROR_CODE, ErrorConstants.ERROR_MESSAGE_CONIFG_KEY);
        }
        try {
            return MasterConfigDao.findByKey(key);
        } catch (DaoException e) {
            logger.error("unable to get value for the given key: {}", key);
           throw new BusinessException(ResponseCode.GENRAL_ERROR);
        }
    }

}
