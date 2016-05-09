package com.as.erp.trade.micro.quotation.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.quotation.dao.QuotationModuleConfigDao;
import com.as.erp.trade.micro.quotation.entity.QuotationModuleConfig;
import com.as.erp.trade.micro.quotation.service.QuotationModuleConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yrx on 2016/5/4.
 */
@Service("quotationModuleConfigService")
public class QuotationModuleConfigServiceImpl extends GenericServiceImpl<QuotationModuleConfig, String> implements QuotationModuleConfigService {

    @Autowired
    private QuotationModuleConfigDao quotationModuleConfigDao;

    @Override
    protected GenericDao<QuotationModuleConfig, String> getDao() {
        return quotationModuleConfigDao;
    }

    @Override
    public QuotationModuleConfig getUnique() {
        List<QuotationModuleConfig> list = quotationModuleConfigDao.getList();
        if (list.size() > 1){
            throw new RuntimeException("multi QuotationModuleConfig founded!");
//            return new R<>(list.get(0)).success(false);
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            return new QuotationModuleConfig();
        }
    }

//    @Override
//    public void save(QuotationModuleConfig entity) {
//        if(getUnique() == null)
//            super.save(entity);
//        else
//            throw new RuntimeException("QuotationModuleConfig 对象已存在, 不能重复生产");
//    }
}
