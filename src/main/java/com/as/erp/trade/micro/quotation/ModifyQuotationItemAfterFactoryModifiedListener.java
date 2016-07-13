package com.as.erp.trade.micro.quotation;

import com.as.common.query.hibernate.Conditions;
import com.as.erp.trade.micro.factory.FactoryNameModifiedEvent;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
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
public class ModifyQuotationItemAfterFactoryModifiedListener implements SmartApplicationListener {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private QuotationProductItemDraftService quotationProductItemDraftService;

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
//        Factory factory = factoryService.getById(factoryId);
        String factoryName = (String) source.get("factoryName");

        List<QuotationProductItemDraft> productItemDraftList = quotationProductItemDraftService
                .getList(Conditions.newInstance().eq("factoryId", factoryId));
        for (QuotationProductItemDraft draft : productItemDraftList) {
            draft.setFactoryName(factoryName);
//            draft.setLinkman(factory.getLinkman());
            quotationProductItemDraftService.update(draft);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }



}
