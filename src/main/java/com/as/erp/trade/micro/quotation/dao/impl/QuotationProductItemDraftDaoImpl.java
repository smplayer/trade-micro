package com.as.erp.trade.micro.quotation.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.quotation.dao.QuotationProductItemDraftDao;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/5/12.
 */
@Repository("quotationProductItemDraftDao")
public class QuotationProductItemDraftDaoImpl extends HibernateGenericDaoImpl<QuotationProductItemDraft, String> implements QuotationProductItemDraftDao {
}
