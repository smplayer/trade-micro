package com.as.erp.trade.micro.quotation;

import com.as.common.query.hibernate.Conditions;
import com.as.erp.trade.micro.quotation.entity.FavorQuotationItem;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.FavorQuotationItemService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yrx on 2016/5/16.
 */
@Component
public class SyncFavorQuotationItemAfterQuotationCustomerNameModifiedListener implements SmartApplicationListener {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private QuotationProductItemDraftService quotationProductItemDraftService;
    @Autowired
    private FavorQuotationItemService favorQuotationItemService;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == QuotationModifiedEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == Quotation.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Quotation quotation = (Quotation) event.getSource();
        FavorQuotationItem item = favorQuotationItemService.get(Conditions.newInstance().eq("quotationId", quotation.getId()));
        if (item != null) {
            item.setCustomerName(quotation.getCustomerName());
            favorQuotationItemService.update(item);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
