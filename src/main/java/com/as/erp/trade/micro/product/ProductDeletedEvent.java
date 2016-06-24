package com.as.erp.trade.micro.product;

import org.springframework.context.ApplicationEvent;

/**
 * Created by yrx on 2016/5/22.
 */
public class ProductDeletedEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ProductDeletedEvent(Object source) {
        super(source);
    }
}
