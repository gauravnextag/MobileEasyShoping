package com.mob.shopping.controller;

import com.mob.shopping.beans.ResponseBean;
import com.mob.shopping.beans.StatusBean;
import com.mob.shopping.beans.request.CheckBankStatusRequest;
import com.mob.shopping.beans.request.DistributorListRequest;
import com.mob.shopping.beans.request.RetailerBalanceRequest;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.service.DistributorServices;
import com.mob.shopping.service.RetailerServices;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DistributorController {

    private static final Logger logger = LoggerFactory.getLogger(DistributorController.class);

    @Autowired
    private DistributorServices distributorServices;


    @RequestMapping(value = "/getDistributors", method = RequestMethod.POST)
    public @ResponseBody
    ResponseBean getDistributors(@RequestBody DistributorListRequest distributorListRequest) {
        ResponseBean response = new ResponseBean(null, null);
        try {
            if(distributorListRequest!=null){
                response.setResult(distributorServices.getDistributors(distributorListRequest));
                response.setStatus(new StatusBean("SUCCESS", "success"));
            }else {
                response.setStatus(new StatusBean(ErrorConstants.ERROR_CODE,ErrorConstants.ERROR_MESSAGE_INVALID_REQUEST));
            }
        } catch (BaseApplicationException e) {
            //	response.setStatus(new StatusBean(e.getError().getErrorCode(),e.getError().getErrorMessage()));
        }catch (Exception e){
            response.setStatus(new StatusBean(ErrorConstants.ERROR_CODE,
                    ErrorConstants.ERROR_MESSAGE_DOWNSTREAM_SYSTEM_DOWN));
        }
        return response;
    }

//    @RequestMapping(value = "/connection/info", method = RequestMethod.POST)
//    public @ResponseBody
//    ResponseBean checkBankStatus(@RequestBody CheckBankStatusRequest checkBankStatusRequest) {
//        ResponseBean response = new ResponseBean(null, null);
////        try {
////
////         logger.info("Request Received for Checking bank open status from {} application for mobile number : {} and " +
////                        "Caf Number {}",checkBankStatusRequest.getAppName(),checkBankStatusRequest.getMsisdn(),checkBankStatusRequest.getCafNumber());
////            response.setResult(retailerServices.checkAccountStatus(checkBankStatusRequest));
////            response.setStatus(new StatusBean("SUCCESS", "success"));
////        } catch (BaseApplicationException e) {
////            response.setStatus(new StatusBean(e.getError().getErrorCode(),e.getError().getErrorMessage()));
////        }catch (Exception e){
////            response.setStatus(new StatusBean(ErrorConstants.ERROR_CODE,
////                    ErrorConstants.ERROR_MESSAGE_DOWNSTREAM_SYSTEM_DOWN));
////        }
//        return response;
//    }
}
