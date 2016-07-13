package com.as.erp.trade.micro.product;

import com.as.common.query.hibernate.Conditions;
import com.as.erp.trade.micro.factory.FactoryDeleteEvent;
import com.as.erp.trade.micro.factory.FactoryNameModifiedEvent;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/5/16.
 */
@Component
public class Product_FactoryDeletedListener implements SmartApplicationListener {

    @Autowired
    private ProductService productService;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == FactoryDeleteEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        String factoryId = (String) event.getSource();

        List<Product> productList = productService.getList(
                Conditions.newInstance().eq("factoryId", factoryId)
        );
        for (Product product : productList) {
            productService.delete(product.getId());
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }


}
