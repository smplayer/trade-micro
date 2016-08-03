package com.as.erp.trade.micro.factory.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yrx on 2016/5/5.
 */
@Controller
public class FactorySearchingController {

    @Autowired
    private FactoryService factoryService;

    @ResponseBody
    @RequestMapping("ajax/factory/search")
    public Object ajaxSearch(
            @RequestParam("name") String name,
            ModelMap modelMap
    ) {
        List<Factory> factoryList = factoryService.getList(Conditions.newInstance().like("name", "%" + name + "%"));
        return factoryList;
    }

    @RequestMapping("factory/createFactoryForProduct")
    public String createFactoryForProduct(

    ) {

        return "factory/createFactoryForProduct";
    }

}
