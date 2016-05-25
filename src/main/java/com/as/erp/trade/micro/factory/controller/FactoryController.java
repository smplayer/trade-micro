package com.as.erp.trade.micro.factory.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @ResponseBody
    @RequestMapping(value = "ajax/factory/create", method = RequestMethod.POST)
    public Object createFactory(
//            @RequestParam String name,
//            @RequestParam String mainProduct,
//            @RequestParam String linkman,
//            @RequestParam String mobileNumber,
//            @RequestParam String phoneNumber,
//            @RequestParam String fax,
//            @RequestParam String qq,
//            @RequestParam String address,
//            @RequestParam String remark

            @RequestBody Map<String, Object> req
    ){
        Factory factory = new Factory();
//        factory.setName(name);
//        factory.setMainProduct(mainProduct);
//        factory.setMobileNumber(mobileNumber);
//        factory.setPhoneNumber(phoneNumber);
//        factory.setFax(fax);
//        factory.setQq(qq);
//        factory.setLinkman(linkman);
//        factory.setAddress(address);
//        factory.setRemark(remark);
        factory.setName((String) req.get("name"));
        factory.setMainProduct((String) req.get("mainProduct"));
        factory.setLinkman((String) req.get("linkman"));
        factory.setMobileNumber((String) req.get("mobileNumber"));
        factory.setPhoneNumber((String) req.get("phoneNumber"));
        factory.setFax((String) req.get("fax"));
        factory.setQq((String) req.get("qq"));
        factory.setAddress((String) req.get("address"));
        factory.setRemark((String) req.get("remark"));
        factory.setCreatedDate(new Date());
        factoryService.save(factory);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @RequestMapping(value = "factory/delete/{id}", method = RequestMethod.GET)
    public String deleteFactory(
            @PathVariable String id
    ){
        factoryService.delete(id);
        return "";
    }

    @RequestMapping(value = "factory/modify/{id}", method = RequestMethod.GET)
    public String modifyFactory(
            @PathVariable String id,
            ModelMap modelMap
    ){
        Factory factory = factoryService.getById(id);
        modelMap.put("factory", factory);
        return "factory/modify";
    }

    @RequestMapping(value = "factory/modify", method = RequestMethod.POST)
    public String modifyFactory(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String mainProduct,
            @RequestParam String linkman,
            @RequestParam String mobileNumber,
            @RequestParam String phoneNumber,
            @RequestParam String fax,
            @RequestParam String qq,
            @RequestParam String address,
            @RequestParam String remark
    ){
        Factory factory = factoryService.getById(id);
        factory.setName(name);
        factory.setMainProduct(mainProduct);
        factory.setMobileNumber(mobileNumber);
        factory.setPhoneNumber(phoneNumber);
        factory.setFax(fax);
        factory.setQq(qq);
        factory.setLinkman(linkman);
        factory.setAddress(address);
        factory.setRemark(remark);
        factoryService.update(factory);
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "ajax/factory/modify", method = RequestMethod.POST)
    public Object modifyFactory(
            @RequestBody Map<String, Object> req
    ){
        Factory factory = factoryService.getById((String) req.get("id"));
        factory.setName((String) req.get("name"));
        factory.setMainProduct((String) req.get("mainProduct"));
        factory.setLinkman((String) req.get("linkman"));
        factory.setMobileNumber((String) req.get("mobileNumber"));
        factory.setPhoneNumber((String) req.get("phoneNumber"));
        factory.setFax((String) req.get("fax"));
        factory.setQq((String) req.get("qq"));
        factory.setAddress((String) req.get("address"));
        factory.setRemark((String) req.get("remark"));
        factoryService.update(factory);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("factory/view/{id}")
    public String get(
            @PathVariable("id") String id,
            ModelMap modelMap
    ){
       Factory factory = factoryService.getById(id);
        modelMap.put("factory", factory);
        return "factory/details";
    }

    @RequestMapping("factory/list")
    public String list(
            @RequestParam(value = "keywords", required = false) String keywords,
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            ModelMap modelMap
    ){

        Query query = new Query();
        query.setPageIndex(pageIndex)
                .setPageSize(pageSize);

        if(StringUtils.isNotBlank(keywords)) {
            query.setConditions(Conditions.newInstance().like("name", "%" + keywords + "%"));
        }

        PageHandler factoryPage = factoryService.getPage(query);
        modelMap.put("page", factoryPage);
        return "factory/list";
    }

}
