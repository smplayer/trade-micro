package com.as.erp.trade.micro.quotation.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.factory.service.FactoryService;
import com.as.erp.trade.micro.product.service.ProductService;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/5/12.
 */
@Controller
public class QuotationOperatingController extends BaseQuotationController {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ProductService productService;

    @RequestMapping("quotation/operating")
    public String operating(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "pageIndex", required = false) Long pageIndex,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            ModelMap modelMap
    ) {
        Quotation quotation = quotationService.getById(id);
        modelMap.put("quotation", quotation);

        String view = "quotation/draft";
        PageHandler page = null;
        page = quotationProductItemDraftService.getPage(
                new Query()
                        .setPageIndex(pageIndex)
                        .setPageSize(pageSize)
                        .setConditions(
                                Conditions.newInstance()
                                        .eq("quotationId", quotation.getId())
                        )
        );
        modelMap.put("page", page);

//        if (quotation != null) {
//            PageHandler page = null;
//            if(Quotation.FLAG_OPERATING_DRAFT == quotation.getOperationFlag()){
//                page = quotationProductItemDraftService.getPage(
//                        new Query()
//                                .setPageIndex(pageIndex)
//                                .setPageSize(pageSize)
//                                .setConditions(
//                                        Conditions.newInstance()
//                                                .eq("quotationId", quotation.getId())
//                                )
//                );
//                modelMap.put("page", page);
//
//                view = "quotation/draft";
//            } else if (Quotation.FLAG_OPERATING == quotation.getOperationFlag()){
//                page = quotationProductItemService.getQuotationProductItemVOPage(
//                        new Query()
//                                .setPageIndex(pageIndex)
//                                .setPageSize(pageSize)
//                                .setConditions(
//                                        Conditions.newInstance()
//                                                .eq("quotationId", quotation.getId())
//                                )
//                );
//                modelMap.put("page", page);
//
//                view = "quotation/operating";
//            }
//        }

        return view;
    }

    @RequestMapping("quotation/confirming/order")
    public String confirmingOrder(
            @RequestParam("id") String id,
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            ModelMap modelMap
    ) {
        Quotation quotation = quotationService.getById(id);
        PageHandler page = quotationProductItemDraftService.getPage(
                new Query().addOrder(Order.desc("addedDate"))
                        .setPageIndex(pageIndex)
                        .setPageSize(pageSize)
                        .setConditions(
                                Conditions.newInstance()
                                        .eq("quotationId", id)
                        )
        );
        modelMap.put("quotation", quotation);
        modelMap.put("page", page);
        return "quotation/confirming-order";
    }

    @RequestMapping("quotation/saveToArchive")
    public String saveToArchive(
            @RequestParam("id") String id
    ) {
        quotationService.saveToArchive(id);
        return "redirect:/quotation/operating/list";
    }

    @RequestMapping("quotation/reloadFromArchive")
    public String reloadFromArchive(
            @RequestParam("id") String id,
            ModelMap modelMap
    ) {
        Quotation operating = quotationService.reloadFromArchive(id);
        modelMap.put("id", operating.getId());
        return "redirect:/quotation/operating";
    }

    @RequestMapping(value = "quotation/findFactory", method = RequestMethod.GET)
    public String findFactory(
            @RequestParam("quotationProductItemDraftId") String quotationProductItemDraftId,
            @RequestParam("keywords") String factoryName,
            ModelMap modelMap
    ){
        PageHandler page = factoryService.getPage(
                new Query().setConditions(
                        Conditions.newInstance()
                        .like("name", "%" + factoryName + "%")
                )
        );

        modelMap.put("page", page);
        modelMap.put("quotationProductItemDraftId", quotationProductItemDraftId);
        return "factory/factory-search";
    }

    @ResponseBody
    @RequestMapping(value = "quotation/selectFactoryForProductItemDraft", method = RequestMethod.POST)
    public Object selectFactoryForProductItemDraft(
            @RequestBody Map<String, Object> req
    ) {
        String id = (String) req.get("id");
        String factoryId = (String) req.get("factoryId");
        quotationProductItemDraftService.selectFactoryForProductItemDraft(id, factoryId);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @ResponseBody
    @RequestMapping("quotation/generateProducts")
    public Object generateProducts(
            @RequestBody Map<String, Object> req
    ) {
        List<String> ids = (List<String>) req.get("ids");
        quotationProductItemDraftService.generateProducts(ids);

        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        map.put("result", "success");
        return map;
    }

}
