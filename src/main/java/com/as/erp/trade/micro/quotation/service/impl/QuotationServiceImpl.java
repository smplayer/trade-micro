package com.as.erp.trade.micro.quotation.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.hibernate.Conditions;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.quotation.dao.QuotationDao;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Quotation getUniqueOperatingQuotation() {
        List<Quotation> list = getList(Conditions.newInstance().eq("operationFlag", Quotation.FLAG_OPERATING));
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new RuntimeException("数据库中出现多个操作中的报价表");
        }
        return null;
    }
}
