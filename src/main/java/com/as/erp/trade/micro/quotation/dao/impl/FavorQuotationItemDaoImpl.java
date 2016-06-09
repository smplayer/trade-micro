package com.as.erp.trade.micro.quotation.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.quotation.dao.FavorQuotationItemDao;
import com.as.erp.trade.micro.quotation.entity.FavorQuotationItem;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/5/27.
 */
@Repository("favorQuotationItemDao")
public class FavorQuotationItemDaoImpl extends HibernateGenericDaoImpl<FavorQuotationItem, String> implements FavorQuotationItemDao {
}
