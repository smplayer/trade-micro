package com.as.erp.trade.micro.product.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.product.dao.ProductDao;
import com.as.erp.trade.micro.product.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/4/29.
 */
@Repository("productDao")
public class ProductDaoImpl extends HibernateGenericDaoImpl<Product, String> implements ProductDao {
}
