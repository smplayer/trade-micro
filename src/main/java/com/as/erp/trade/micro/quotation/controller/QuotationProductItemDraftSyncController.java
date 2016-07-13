package com.as.erp.trade.micro.quotation.controller;

import com.as.common.query.hibernate.Conditions;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import com.as.erp.trade.micro.quotation.QuotationProductItemDraftPropModifiedEvent;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import com.as.erp.trade.micro.quotation.vo.QuotationProductItemDraftPropModifiedVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yrx on 2016/5/13.
 */
@Controller
public class QuotationProductItemDraftSyncController {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ApplicationContext applicationContext;

    @Resource(name = "quotationProductItemDraftService")
    private QuotationProductItemDraftService quotationProductItemDraftService;

    @ResponseBody
    @RequestMapping("ajax/quotation/productItemDraft/create")
    public Object createItem(
            @RequestBody Map req
    ) {

        QuotationProductItemDraft draft = new QuotationProductItemDraft();
        draft.setQuotationId((String) req.get("quotationId"));
        draft.setAddedDate(new Date());

        draft.setFactoryProductName((String) req.get("factoryProductName"));
        draft.setFactoryProductNo((String) req.get("factoryProductNo"));
        draft.setPackageForm((String) req.get("packageForm"));
        draft.setUnit((String) req.get("unit"));

        if (StringUtils.isNotBlank((String)req.get("factoryPrice")))
            draft.setFactoryPrice(Double.valueOf((String) req.get("factoryPrice")));

        draft.setCartonSize((String) req.get("cartonSize"));

        if (StringUtils.isNotBlank((String)req.get("packingQuantity")))
        draft.setPackingQuantity(Integer.valueOf((String) req.get("packingQuantity")));

        if (StringUtils.isNotBlank((String)req.get("grossWeight")))
            draft.setGrossWeight(Double.valueOf((String) req.get("grossWeight")));

        if (StringUtils.isNotBlank((String)req.get("netWeight")))
            draft.setNetWeight(Double.valueOf((String) req.get("netWeight")));

        if (StringUtils.isNotBlank((String)req.get("orderedCartonQuantity")))
            draft.setOrderedCartonQuantity(Integer.valueOf((String) req.get("orderedCartonQuantity")));

        if (StringUtils.isNotBlank((String)req.get("quotedPrice")))
            draft.setQuotedPrice(Double.valueOf((String) req.get("quotedPrice")));

        draft.setFactoryName((String) req.get("factoryName"));
        draft.setLinkman((String) req.get("linkman"));
        draft.setContactNumber((String) req.get("contactNumber"));


        Factory factory = factoryService.get(Conditions.newInstance().eq("mobileNumber", draft.getContactNumber()));
        if (factory != null) {
            draft.setFactoryId(factory.getId());
            draft.setFactoryName(factory.getName());
            draft.setLinkman(factory.getLinkman());
            draft.setContactNumber(factory.getMobileNumber());
        }


        quotationProductItemDraftService.save(draft);

        QuotationProductItemDraftPropModifiedVO modified = new QuotationProductItemDraftPropModifiedVO();
        modified.setId(draft.getId());

        modified.setPropertyName("factoryPrice");
        modified.setPropertyValue(draft.getFactoryPrice());
        applicationContext.publishEvent(new QuotationProductItemDraftPropModifiedEvent(modified));

        modified.setPropertyName("cartonSize");
        modified.setPropertyValue(draft.getCartonSize());
        applicationContext.publishEvent(new QuotationProductItemDraftPropModifiedEvent(modified));

        modified.setPropertyName("packingQuantity");
        modified.setPropertyValue(draft.getPackingQuantity());
        applicationContext.publishEvent(new QuotationProductItemDraftPropModifiedEvent(modified));

        modified.setPropertyName("orderedCartonQuantity");
        modified.setPropertyValue(draft.getOrderedCartonQuantity());
        applicationContext.publishEvent(new QuotationProductItemDraftPropModifiedEvent(modified));

        modified.setPropertyName("factoryProductName");
        modified.setPropertyValue(draft.getOrderedCartonQuantity());
        applicationContext.publishEvent(new QuotationProductItemDraftPropModifiedEvent(modified));


        Map<String, Object> map = new HashMap<>();
        map.put("item", draft);

        return map;
    }

    @ResponseBody
    @RequestMapping("ajax/quotation/productItemDraft/modifyProp")
    public Object modifyItem(
            @RequestBody Map req
    ) {
        String id = (String) req.get("id");
        String propName = (String) req.get("propName");
        Object propValue = req.get("propValue");
//        Integer lineNumber = Integer.valueOf(req.get("lineNumber") != null ? req.get("lineNumber").toString() : "0");

        QuotationProductItemDraft item = quotationProductItemDraftService.modify(id, propName, propValue);

        Map<String, Object> map = new HashMap<>();
//        map.put("lineNumber", lineNumber);
        map.put("propName", propName);
        map.put("item", item);
        map.put("result", "success");

        return map;
    }


}
