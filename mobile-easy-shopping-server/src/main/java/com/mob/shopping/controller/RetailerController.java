package com.mob.shopping.controller;

import com.mob.shopping.beans.ErrorBean;
import com.mob.shopping.beans.ResponseBean;
import com.mob.shopping.beans.StatusBean;
import com.mob.shopping.beans.request.CheckBankStatusRequest;
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
    ProducerTemplate producerTemplate;


    @Autowired
    private RetailerServices retailerServices;


    @RequestMapping(value = "/retailer/checkBalance", method = RequestMethod.POST)
    public @ResponseBody
    ResponseBean checkRetailerBalance(@RequestBody RetailerBalanceRequest retailerBalanceRequest) {
        ResponseBean response = new ResponseBean(null, null);
        logger.info("Request Received for Checking Retailer account Balance having lapu Number {} and mobile Number",
                new Object[]{retailerBalanceRequest.getLapuNumber(), retailerBalanceRequest.getMsisdn()});
//        try {
//            response.setResult(retailerServices.checkRetailerBalance(retailerBalanceRequest));
//            response.setStatus(new StatusBean("SUCCESS", "success"));
//        } catch (BaseApplicationException e) {
//            response.setStatus(new StatusBean(e.getError().getErrorCode(),e.getError().getErrorMessage()));
//        }catch (Exception e){
//            response.setStatus(new StatusBean(ErrorConstants.ERROR_CODE,
//                    ErrorConstants.ERROR_MESSAGE_DOWNSTREAM_SYSTEM_DOWN));
//        }
        return response;
    }

    @RequestMapping(value = "/connection/info", method = RequestMethod.POST)
    public @ResponseBody
    ResponseBean checkBankStatus(@RequestBody CheckBankStatusRequest checkBankStatusRequest) {
        ResponseBean response = new ResponseBean(null, null);
//        try {
//
//         logger.info("Request Received for Checking bank open status from {} application for mobile number : {} and " +
//                        "Caf Number {}",checkBankStatusRequest.getAppName(),checkBankStatusRequest.getMsisdn(),checkBankStatusRequest.getCafNumber());
//            response.setResult(retailerServices.checkAccountStatus(checkBankStatusRequest));
//            response.setStatus(new StatusBean("SUCCESS", "success"));
//        } catch (BaseApplicationException e) {
//            response.setStatus(new StatusBean(e.getError().getErrorCode(),e.getError().getErrorMessage()));
//        }catch (Exception e){
//            response.setStatus(new StatusBean(ErrorConstants.ERROR_CODE,
//                    ErrorConstants.ERROR_MESSAGE_DOWNSTREAM_SYSTEM_DOWN));
//        }
        return response;
    }
}
