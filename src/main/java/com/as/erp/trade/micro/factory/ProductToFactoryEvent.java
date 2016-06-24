package com.as.erp.trade.micro.factory;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * Created by yrx on 2016/5/16.
 */
public class ProductToFactoryEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ProductToFactoryEvent(Map<String, Object> source) {
        super(source);
    }
}
