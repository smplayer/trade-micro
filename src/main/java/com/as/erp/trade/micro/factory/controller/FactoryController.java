package com.as.erp.trade.micro.factory.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.factory.FactoryNameModifiedEvent;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-4-26.
 */
@Controller
public class FactoryController {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private QuotationProductItemDraftService quotationProductItemDraftService;
    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "factory/create", method = RequestMethod.GET)
    public String createFactory(){
        return "factory/create";
    }

    @ResponseBody
    @RequestMapping(value = "ajax/factory/create", method = RequestMethod.POST)
    public Object createFactory(
            @RequestBody Map<String, Object> req
    ){
        Factory factory = new Factory();
        factory.setName((String) req.get("name"));
        factory.setMainProduct((String) req.get("mainProduct"));
        factory.setLinkman((String) req.get("linkman"));
        factory.setMobileNumber((String) req.get("mobileNumber"));
        factory.setPhoneNumber((String) req.get("phoneNumber"));
        factory.setFax((String) req.get("fax"));
        factory.setQq((String) req.get("qq"));
        factory.setAddress((String) req.get("address"));
        factory.setSummary((String) req.get("summary"));
        factory.setAddedDate(new Date());
        factoryService.save(factory);

        //在为见客下单模块进行工厂查新过程中新建工厂时, 同步保存工厂id到draft
        String draftId = (String) req.get("draftId");
        if (StringUtils.isNotBlank(draftId)) {
            QuotationProductItemDraft draft = quotationProductItemDraftService.getById(draftId);
            draft.setFactoryId(factory.getId());
            draft.setFactoryName(factory.getName());
            quotationProductItemDraftService.update(draft);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @RequestMapping(value = "factory/delete/{id}")
    public String deleteFactory(
            @PathVariable String id
    ){
        factoryService.delete(id);
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "factory/delete")
    public Object deleteFactory(
            @RequestBody List<String> ids
    ){
//        List<String> ids = (List<String>) req.get("ids");
        factoryService.delete(ids);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
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
            @RequestParam String summary
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
        factory.setSummary(summary);
        factoryService.update(factory);
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "ajax/factory/modify", method = RequestMethod.POST)
    public Object modifyFactory(
            @RequestBody Map<String, Object> req
    ){
        Factory factory = factoryService.getById((String) req.get("id"));

        boolean factoryNameModified = !factory.getName().equals(req.get("name"));

        factory.setName((String) req.get("name"));
        factory.setMainProduct((String) req.get("mainProduct"));
        factory.setLinkman((String) req.get("linkman"));
        factory.setMobileNumber((String) req.get("mobileNumber"));
        factory.setPhoneNumber((String) req.get("phoneNumber"));
        factory.setFax((String) req.get("fax"));
        factory.setQq((String) req.get("qq"));
        factory.setAddress((String) req.get("address"));
        factory.setSummary((String) req.get("summary"));
        factoryService.update(factory);

        if (factoryNameModified) {
            Map<String, Object> source = new HashMap<>();
            source.put("factoryId", factory.getId());
            source.put("factoryName", factory.getName());
            applicationContext.publishEvent(new FactoryNameModifiedEvent(source));
        }

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

    @ResponseBody
    @RequestMapping("factory/getFactoryInfo")
    public Object getFactoryInfo(
            @RequestBody Map<String, String> req
    ){
        String id = req.get("id");
        Factory factory = factoryService.getById(id);
        return factory;
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
                .setPageSize(pageSize)
                .addOrder(Order.desc("addedDate"));

        Conditions conditions = Conditions.newInstance();

        if(StringUtils.isNotBlank(keywords)) {
            query.setConditions(
                    conditions.or(
                            Conditions.newInstance()
                                    .like("name", "%" + keywords + "%")
                                    .like("mainProduct", "%" + keywords + "%")
                                    .like("linkman", "%" + keywords + "%")
                                    .like("mobileNumber", "%" + keywords + "%")
                                    .like("address", "%" + keywords + "%")
                    )
            );
        }

        PageHandler factoryPage = factoryService.getPage(query);
        modelMap.put("page", factoryPage);
        return "factory/list";
    }

}
