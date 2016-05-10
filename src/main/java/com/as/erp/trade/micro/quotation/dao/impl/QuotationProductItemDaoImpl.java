package com.as.erp.trade.micro.quotation.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.quotation.dao.QuotationProductItemDao;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItem;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/5/10.
 */
@Repository("quotationProductItemDao")
public class QuotationProductItemDaoImpl extends HibernateGenericDaoImpl<QuotationProductItem, String> implements QuotationProductItemDao {
}
