package com.as.erp.trade.micro.quotation.service;

import com.as.common.service.GenericService;
import com.as.erp.trade.micro.quotation.entity.FavorQuotationItem;
import com.as.user.entity.User;

/**
 * Created by yrx on 2016/5/27.
 */
public interface FavorQuotationItemService extends GenericService<FavorQuotationItem, String> {
    void addToSpecifiedPosition(String quotationId, Integer indexNumber, User user, boolean move);
    void addToFront(String quotationId, User user);
}
