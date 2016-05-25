package com.as.erp.trade.micro.quotation.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        );
        modelMap.put("page", page);
        return "quotation/archive-list";
    }

}
