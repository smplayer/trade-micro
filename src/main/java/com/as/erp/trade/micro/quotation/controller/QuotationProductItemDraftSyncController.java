package com.as.erp.trade.micro.quotation.controller;

import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import org.springframework.beans.factory.annotation.Autowired;
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
//        draft.setFactoryProductName((String) req.get("factoryProductName"));
//        draft.setFactoryProductNo((String) req.get("factoryProductNo"));
//        draft.setFactoryProductNo((String) req.get("packageForm"));
//        draft.setFactoryProductNo((String) req.get("unit"));
//        draft.setFactoryProductNo((String) req.get("factoryPrice"));
//        draft.setFactoryProductNo((String) req.get("cartonSize"));
//        draft.setFactoryProductNo((String) req.get("packingQuantity"));
//        draft.setFactoryProductNo((String) req.get("grossWeight"));
//        draft.setFactoryProductNo((String) req.get("netWeight"));
//        draft.setFactoryProductNo((String) req.get("orderedCartonQuantity"));
//        draft.setFactoryProductNo((String) req.get("quotedPrice"));
//        draft.setFactoryProductNo((String) req.get("factoryName"));
//        draft.setFactoryProductNo((String) req.get("quotedPrice"));
        quotationProductItemDraftService.save(draft);

        Map<String, Object> map = new HashMap<>();
        map.put("item", draft);

        return map;
    }

    @ResponseBody
    @RequestMapping("ajax/quotation/productItemDraft/modifyProp")
    public Object modifyItem(
            @RequestBody Map req
    ){
        String id = (String) req.get("id");
        String propName = (String) req.get("propName");
        Object propValue = req.get("propValue");
        Integer lineNumber = Integer.valueOf(req.get("lineNumber").toString());

        QuotationProductItemDraft item = quotationProductItemDraftService.modify(id, propName, propValue);

        Map<String, Object> map = new HashMap<>();
        map.put("lineNumber", lineNumber);
        map.put("modifiedPropName", propName);
        map.put("item", item);
        map.put("result", "success");

        return map;
    }



}
