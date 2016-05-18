package com.as.erp.trade.micro.quotation.controller;

import com.as.common.Base;
import com.as.erp.trade.micro.quotation.service.QuotationModuleConfigService;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yrx on 2016/5/4.
 */
public abstract class BaseQuotationController extends Base {

    @Autowired
    protected QuotationService quotationService;
    @Autowired
    protected QuotationProductItemService quotationProductItemService;
    @Autowired
    protected QuotationProductItemDraftService quotationProductItemDraftService;
    @Autowired
    protected QuotationModuleConfigService quotationModuleConfigService;

}
