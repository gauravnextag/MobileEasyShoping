package com.mob.shopping.controller;

import com.mob.shopping.beans.ResponseBean;
import com.mob.shopping.beans.StatusBean;
import com.mob.shopping.beans.request.CheckBankStatusRequest;
import com.mob.shopping.beans.request.DistrictRequest;
import com.mob.shopping.beans.request.RetailerBalanceRequest;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.service.RegionServices;
import com.mob.shopping.service.RetailerServices;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegionController {

    private static final Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    private RegionServices regionServices;


    @RequestMapping(value = "/getStates", method = RequestMethod.POST)
    public @ResponseBody ResponseBean getStates() {
        ResponseBean response = new ResponseBean(null, null);
        try {
            response.setResult(regionServices.getStates());
            response.setStatus(new StatusBean("SUCCESS", "success"));
        } catch (BaseApplicationException e) {
            response.setStatus(new StatusBean(e.getError().getErrorCode(),e.getError().getErrorMessage()));
        }catch (Exception e){
            response.setStatus(new StatusBean(ErrorConstants.ERROR_CODE,
                    ErrorConstants.ERROR_MESSAGE_DOWNSTREAM_SYSTEM_DOWN));
        }
        return response;
    }

    @RequestMapping(value = "/getDistrict", method = RequestMethod.POST)
    public @ResponseBody ResponseBean getDistrict(@RequestBody DistrictRequest districtRequest) {
        ResponseBean response = new ResponseBean(null, null);
        try {
            if(districtRequest!=null){
                response.setResult(regionServices.getDistrictRequest(districtRequest));
                response.setStatus(new StatusBean("SUCCESS", "success"));
            }else {
                response.setStatus(new StatusBean(ErrorConstants.ERROR_CODE,ErrorConstants.ERROR_MESSAGE_INVALID_REQUEST));
            }
        } catch (BaseApplicationException e) {
            response.setStatus(new StatusBean(e.getError().getErrorCode(),e.getError().getErrorMessage()));
        }catch (Exception e){
            response.setStatus(new StatusBean(ErrorConstants.ERROR_CODE,
                    ErrorConstants.ERROR_MESSAGE_DOWNSTREAM_SYSTEM_DOWN));
        }
        return response;
    }
}
