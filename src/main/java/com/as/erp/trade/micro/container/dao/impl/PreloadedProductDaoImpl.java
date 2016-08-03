package com.as.erp.trade.micro.container.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.container.dao.PreloadedProductItemDao;
import com.as.erp.trade.micro.container.entity.PreloadedProductItem;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/7/31.
 */
@Repository("preloadedProductItemDao")
public class PreloadedProductDaoImpl extends HibernateGenericDaoImpl<PreloadedProductItem, String> implements PreloadedProductItemDao {
}
