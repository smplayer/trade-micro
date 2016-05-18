package com.as.erp.trade.micro.quotation.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.quotation.dao.QuotationOperating2ArchiveDao;
import com.as.erp.trade.micro.quotation.entity.QuotationOperating2Archive;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/5/18.
 */
@Repository("quotationOperating2ArchiveDao")
public class QuotationOperating2ArchiveDaoImpl extends HibernateGenericDaoImpl<QuotationOperating2Archive, String> implements QuotationOperating2ArchiveDao {
}
