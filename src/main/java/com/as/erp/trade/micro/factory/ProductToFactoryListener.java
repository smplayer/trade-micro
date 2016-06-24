package com.as.erp.trade.micro.factory;

import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
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
public class ProductToFactoryListener implements SmartApplicationListener {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ProductService productService;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == ProductToFactoryEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Map<String, Object> source = (Map<String, Object>) event.getSource();
        Product product = (Product) source.get("product");

        String oldFactoryId = null;
        if(product.getId() != null) {
            Product oldProduct = productService.getById(product.getId());
            oldFactoryId = oldProduct.getFactoryId();
        }
        if (StringUtils.isNotBlank(oldFactoryId)) {
            Factory oldFactory = factoryService.getById(oldFactoryId);
            oldFactory.setProductQuantity(oldFactory.getProductQuantity() - 1);
            factoryService.update(oldFactory);
        }

        String newFactoryId = (String) source.get("newFactoryId");
        if (StringUtils.isNotBlank(newFactoryId)) {
            Factory newFactory = factoryService.getById(newFactoryId);
            newFactory.setProductQuantity(newFactory.getProductQuantity() + 1);
            factoryService.update(newFactory);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }



}
