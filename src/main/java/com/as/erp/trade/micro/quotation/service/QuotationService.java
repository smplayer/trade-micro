package com.as.erp.trade.micro.quotation.service;

import com.as.common.query.hibernate.Query;
import com.as.common.service.GenericService;
import com.as.erp.trade.micro.quotation.entity.Quotation;

import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/5/4.
 */
public interface QuotationService extends GenericService<Quotation, String> {

    Quotation getUniqueOperatingQuotation();

    Quotation saveToArchive(String id);
    Quotation reloadFromArchive(String id);
    Quotation copyFromArchive(String id);
    Quotation getOperatingOrReloadFromArchive(String id);

    Map<String, Object> getTotalValuesOfArchiveListAsMap(List<Quotation> archiveList);

}
