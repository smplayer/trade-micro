package com.as.erp.trade.micro.product;

import com.as.common.query.hibernate.Conditions;
import com.as.erp.trade.micro.factory.FactoryNameModifiedEvent;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import com.as.erp.trade.micro.quotation.QuotationModifiedEvent;
import com.as.erp.trade.micro.quotation.QuotationProductItemDraftPropModifiedEvent;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import com.as.erp.trade.micro.quotation.vo.QuotationProductItemDraftPropModifiedVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/5/16.
 */
@Component
public class ModifyProductAfterFactoryModifiedListener implements SmartApplicationListener {

    @Autowired
    private ProductService productService;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == FactoryNameModifiedEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Map<String, Object> source = (Map<String, Object>) event.getSource();
        String factoryId = (String) source.get("factoryId");
        String factoryName = (String) source.get("factoryName");

        List<Product> productList = productService.getList(
                Conditions.newInstance().eq("factoryId", factoryId)
        );
        for (Product product : productList) {
            product.setFactoryName(factoryName);
            productService.update(product);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Double round(Double value, Integer scale) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }


}
