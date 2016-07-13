package com.as.erp.trade.micro.factory;

import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import com.as.erp.trade.micro.product.ProductDeletedEvent;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by yrx on 2016/5/16.
 */
@Component
public class ProductDeletedListener implements SmartApplicationListener {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ProductService productService;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == ProductDeletedEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        String productId = (String) event.getSource();

        Product product = productService.getById(productId);
        if (StringUtils.isNotBlank(product.getFactoryId())) {
            Factory factory = factoryService.getById(product.getFactoryId());
            if (factory != null) {
                factory.setProductQuantity(factory.getProductQuantity() - 1);
                factoryService.update(factory);
            }
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }



}
