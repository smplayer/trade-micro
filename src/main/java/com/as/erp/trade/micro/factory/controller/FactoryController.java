package com.as.erp.trade.micro.factory.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 16-4-26.
 */
@Controller
public class FactoryController {

    @Autowired
    private FactoryService factoryService;

    @RequestMapping(value = "factory/create", method = RequestMethod.GET)
    public String createFactory(){
        return "factory/create";
    }

    @RequestMapping(value = "factory/create", method = RequestMethod.POST)
    public String createFactory(
            @RequestParam String name,
            @RequestParam String mainProduct,
            @RequestParam Integer productQuantity,
            @RequestParam String linkman,
            @RequestParam String contactNumber,
            @RequestParam String address,
            @RequestParam String remark
    ){
        Factory factory = new Factory();
        factory.setName(name);
        factory.setMainProduct(mainProduct);
        factory.setProductQuantity(productQuantity);
        factory.setLinkman(linkman);
        factory.setContactNumber(contactNumber);
        factory.setAddress(address);
        factory.setRemark(remark);
        factoryService.save(factory);

        return "redirect:/factory/create";
    }

    @RequestMapping(value = "factory/delete/{id}", method = RequestMethod.GET)
    public String deleteFactory(
            @PathVariable String id
    ){
        factoryService.delete(id);
        return "";
    }

    @RequestMapping(value = "factory/modify", method = RequestMethod.GET)
    public String modifyFactory(){
        return "factory/modify";
    }

    @RequestMapping(value = "factory/modify", method = RequestMethod.POST)
    public String modifyFactory(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String mainProduct,
            @RequestParam Integer productQuantity,
            @RequestParam String linkman,
            @RequestParam String contactNumber,
            @RequestParam String address,
            @RequestParam String remark
    ){
        Factory factory = factoryService.getById(id);
        factory.setName(name);
        factory.setMainProduct(mainProduct);
        factory.setProductQuantity(productQuantity);
        factory.setLinkman(linkman);
        factory.setContactNumber(contactNumber);
        factory.setAddress(address);
        factory.setRemark(remark);
        factoryService.update(factory);

        return "";
    }

    @RequestMapping("factory/{id}")
    public String get(
            @PathVariable("id") String id,
            ModelMap modelMap
    ){
       Factory factory = factoryService.getById(id);
        modelMap.put("factory", factory);
        return "factory/factory-details";
    }

    @RequestMapping("factory/list")
    public String list(
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            ModelMap modelMap
    ){

        Query query = new Query();
        query.setPageIndex(pageIndex)
                .setPageSize(pageSize);

        PageHandler factoryPage = factoryService.getPage(query);
        modelMap.put("factoryPage", factoryPage);
        return "factory/factory-list";
    }

}
