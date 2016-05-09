package com.as.erp.trade.micro.quotation.service;

import com.as.common.R;
import com.as.common.service.GenericService;
import com.as.erp.trade.micro.quotation.entity.QuotationModuleConfig;

/**
 * Created by yrx on 2016/5/4.
 */
public interface QuotationModuleConfigService extends GenericService<QuotationModuleConfig, String> {

    QuotationModuleConfig getUnique();

}
