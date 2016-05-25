package com.as.erp.trade.micro.system.controller;

import com.as.erp.trade.micro.system.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yrx on 2016/5/19.
 */
@Controller
public class Config1Controller {

    @RequestMapping("system/config1")
    public String config1(){
        return "system/config1/config1";
    }

    @RequestMapping(value = "system/config1", method = RequestMethod.POST)
    public String config1(
            @RequestParam("startProductNumber") String startProductNumber,
            @RequestParam("startQuotationSerialNumber") String startQuotationSerialNumber
    ){
        Config config = new Config();
        config.setStartProductNumber(startProductNumber);
        config.setStartQuotationSerialNumber(startQuotationSerialNumber);
        return "system/config1/config1";
    }

}
