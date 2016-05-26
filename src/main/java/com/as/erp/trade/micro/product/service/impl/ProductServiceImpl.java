package com.as.erp.trade.micro.product.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.product.ProductModifiedEvent;
import com.as.erp.trade.micro.product.dao.ProductDao;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/4/29.
 */
@Service("productService")
public class ProductServiceImpl extends GenericServiceImpl<Product, String> implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected GenericDao<Product, String> getDao() {
        return productDao;
    }

    @Override
    public void update(Product entity) {
        applicationContext.publishEvent(new ProductModifiedEvent(entity));
        super.update(entity);
    }

    @Override
    public Product selectFactory(String id, String factoryId) {
        Product product = getById(id);
        product.setFactoryId(factoryId);
        update(product);
        return product;
    }
}
