package com.as.erp.trade.micro.product.service;

import com.as.common.service.GenericService;
import com.as.erp.trade.micro.product.entity.Product;

/**
 * Created by yrx on 2016/4/29.
 */
public interface ProductService extends GenericService<Product, String> {

    Product selectFactory(String id, String factoryId);

    void copyProducts(String targetId, Integer count);

}
