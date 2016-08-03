package com.as.erp.trade.micro.product.service;

import com.as.common.service.GenericService;
import com.as.erp.trade.micro.product.entity.Product;

import java.util.Collection;
import java.util.Map;

/**
 * Created by yrx on 2016/4/29.
 */
public interface ProductService extends GenericService<Product, String> {

    Product selectFactory(String id, String factoryId);

    void copyProducts(String targetId, Integer count);

    Map<String, Object> generateCompanyProductNo(Collection<String> productIds);

}
