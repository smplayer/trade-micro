package com.as.erp.trade.micro.quotation.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/5/19.
 */
@Controller
public class QuotationArchiveController {

    @Autowired
    private QuotationService quotationService;

    @RequestMapping("quotation/archiveList")
    public String archiveList(
            @RequestParam(value = "pageIndex", required = false) Long pageIndex,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            ModelMap modelMap
    ) {
        PageHandler page = quotationService.getPage(
                new Query()
                        .setPageIndex(pageIndex)
                        .setPageSize(pageSize)
                        .setConditions(
                                Conditions.newInstance()
                                        .eq("operationFlag", Quotation.FLAG_ARCHIVED)
                        )
                        .addOrder(Order.desc("archivedDate"))
        );

        Map<String, Object> totalValueMap = quotationService.getTotalValuesOfArchiveListAsMap((List<Quotation>) page.getDataList());

        modelMap.put("page", page);
        modelMap.put("totalValueMap", totalValueMap);
        return "quotation/archive-list";
    }

    @ResponseBody
    @RequestMapping("quotation/deleteArchive")
    public Object deleteArchive(
            @RequestBody Map<String, Object> req
    ) {
        List<String> ids = (List<String>) req.get("ids");
        quotationService.delete(ids);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

}
