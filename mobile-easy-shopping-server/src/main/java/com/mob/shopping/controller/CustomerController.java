package com.mob.shopping.controller;

import com.mob.shopping.beans.ResponseBean;
import com.mob.shopping.beans.request.CheckBankStatusRequest;
import com.mob.shopping.beans.request.RetailerBalanceRequest;
import com.mob.shopping.service.RetailerServices;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

}
