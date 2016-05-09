package com.as.erp.trade.micro.quotation.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.quotation.dao.QuotationDao;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/5/4.
 */
@Service("quotationService")
public class QuotationServiceImpl extends GenericServiceImpl<Quotation, String> implements QuotationService {

    @Autowired
    private QuotationDao quotationDao;

    @Override
    protected GenericDao<Quotation, String> getDao() {
        return quotationDao;
    }

    @Override
    public void save(Quotation entity) {
        super.save(entity);
    }
}
