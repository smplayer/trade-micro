package com.as.erp.trade.micro.quotation.service;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.quotation.dao.QuotationOperating2ArchiveDao;
import com.as.erp.trade.micro.quotation.entity.QuotationOperating2Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/5/18.
 */
@Service("quotationOperating2ArchiveService")
public class QuotationOperating2ArchiveServiceImpl extends GenericServiceImpl<QuotationOperating2Archive, String> implements QuotationOperating2ArchiveService {

    @Autowired
    private QuotationOperating2ArchiveDao quotationOperating2ArchiveDao;

    @Override
    protected GenericDao<QuotationOperating2Archive, String> getDao() {
        return quotationOperating2ArchiveDao;
    }
}
