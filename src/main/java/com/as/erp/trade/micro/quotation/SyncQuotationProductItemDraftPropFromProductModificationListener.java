package com.as.erp.trade.micro.quotation;

import com.as.common.query.hibernate.Conditions;
import com.as.erp.trade.micro.product.ProductModifiedEvent;
import com.as.erp.trade.micro.product.entity.Product;
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
import java.util.List;

/**
 * Created by yrx on 2016/5/16.
 */
@Component
public class SyncQuotationProductItemDraftPropFromProductModificationListener implements SmartApplicationListener {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private QuotationProductItemDraftService quotationProductItemDraftService;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == ProductModifiedEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == Product.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Product product = (Product) event.getSource();
        List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
                Conditions.newInstance()
                .eq("productId", product.getId())
        );
        for (QuotationProductItemDraft draft : draftList) {
            draft.setFunctionDescription(product.getFunctionDescription());
//            draft.setCompanyProductNo(product.getCompanyProductNo());
        }
        quotationProductItemDraftService.update(draftList);
    }

    @Override
    public int getOrder() {
        return 0;
    }




}
