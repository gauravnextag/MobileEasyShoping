package com.mob.shopping.controller;

import com.mob.shopping.beans.ErrorBean;
import com.mob.shopping.beans.ResponseBean;
import com.mob.shopping.beans.StatusBean;
import com.mob.shopping.beans.request.CheckBankStatusRequest;
import com.mob.shopping.beans.request.DistrictRequest;
import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.beans.request.RetailerBalanceRequest;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.service.RetailerServices;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RetailerController {

    private static final Logger logger = LoggerFactory.getLogger(RetailerController.class);


    @Autowired
    private RetailerServices retailerServices;


    @RequestMapping(value = "/retailer/registration", method = RequestMethod.POST)
    public @ResponseBody ResponseBean getDistrict(@RequestBody RegistrationRequest registrationRequest) {
        ResponseBean response = new ResponseBean(null, null);
        try {
            if(registrationRequest!=null){
                retailerServices.register(registrationRequest);
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
