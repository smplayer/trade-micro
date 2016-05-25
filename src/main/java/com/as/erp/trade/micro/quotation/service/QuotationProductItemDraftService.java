package com.as.erp.trade.micro.quotation.service;

import com.as.common.service.GenericService;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.vo.QuotationAccumulativeTotal;

import java.util.List;

/**
 * Created by yrx on 2016/5/12.
 */
public interface QuotationProductItemDraftService extends GenericService<QuotationProductItemDraft, String> {
    QuotationProductItemDraft modify(String id, String propertyName, Object propertyValue);
    void generateProducts(List<String> quotationProductItemDraftIds);
    void selectFactoryForProductItemDraft(String id, String factoryId);
    QuotationAccumulativeTotal getQuotationAccumulativeTotal(String quotationId, Long pageIndex, Integer pageSize);
}
