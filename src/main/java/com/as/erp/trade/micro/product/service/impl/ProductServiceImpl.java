package com.as.erp.trade.micro.product.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.product.dao.ProductDao;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/4/29.
 */
@Service("productService")
public class ProductServiceImpl extends GenericServiceImpl<Product, String> implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    protected GenericDao<Product, String> getDao() {
        return productDao;
    }


}
