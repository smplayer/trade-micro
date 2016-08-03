package com.as.erp.trade.micro.order.service;

import com.as.common.query.PageHandler;
import com.as.common.service.GenericService;
import com.as.erp.trade.micro.order.entity.OrderProductItem;

import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/6/24.
 */
public interface OrderProductItemService extends GenericService<OrderProductItem, String> {

    List<OrderProductItem> getListByFavor(String favorId);

    List<OrderProductItem> getScheduledListByFavor(String favorId);

    OrderProductItem modifyProp(String id, String propName, Object propValue);

    Map<String, Object> getAccumulativeTotal(String favorId, Long pageIndex, Integer pageSize);

}
