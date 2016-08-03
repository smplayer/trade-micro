package com.as.erp.trade.micro.quotation.controller;

import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by yrx on 2016/7/15.
 */
public class BaseQuotationController {
    @Autowired
    protected QuotationService quotationService;
    @Autowired
    protected QuotationProductItemDraftService quotationProductItemDraftService;
    @Autowired
    protected ApplicationContext applicationContext;
}
