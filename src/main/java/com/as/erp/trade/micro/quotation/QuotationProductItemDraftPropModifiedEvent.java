package com.as.erp.trade.micro.quotation;

import com.as.erp.trade.micro.quotation.vo.QuotationProductItemDraftPropModifiedVO;
import org.springframework.context.ApplicationEvent;

/**
 * Created by yrx on 2016/5/16.
 */
public class QuotationProductItemDraftPropModifiedEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public QuotationProductItemDraftPropModifiedEvent(QuotationProductItemDraftPropModifiedVO source) {
        super(source);
    }
}
