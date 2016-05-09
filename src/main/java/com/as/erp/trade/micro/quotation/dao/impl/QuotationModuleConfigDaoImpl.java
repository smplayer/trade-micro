package com.as.erp.trade.micro.quotation.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.quotation.dao.QuotationModuleConfigDao;
import com.as.erp.trade.micro.quotation.entity.QuotationModuleConfig;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/5/4.
 */
@Repository("quotationModuleConfigDao")
public class QuotationModuleConfigDaoImpl extends HibernateGenericDaoImpl<QuotationModuleConfig, String> implements QuotationModuleConfigDao {
}
