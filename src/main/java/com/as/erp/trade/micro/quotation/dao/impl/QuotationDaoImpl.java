package com.as.erp.trade.micro.quotation.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.quotation.dao.QuotationDao;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/5/4.
 */
@Repository("quotationDao")
public class QuotationDaoImpl extends HibernateGenericDaoImpl<Quotation, String> implements QuotationDao {
}
