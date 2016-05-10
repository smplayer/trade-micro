package com.as.erp.trade.micro.quotation.service;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Query;
import com.as.common.service.GenericService;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItem;

/**
 * Created by yrx on 2016/5/10.
 */
public interface QuotationProductItemService extends GenericService<QuotationProductItem, String> {
    PageHandler getQuotationProductItemVOPage(Query query);
}
