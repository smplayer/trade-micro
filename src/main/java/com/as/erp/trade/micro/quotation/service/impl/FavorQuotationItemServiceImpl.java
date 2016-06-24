package com.as.erp.trade.micro.quotation.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.quotation.dao.FavorQuotationItemDao;
import com.as.erp.trade.micro.quotation.entity.FavorQuotationItem;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.service.FavorQuotationItemService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import com.as.user.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/5/27.
 */
@Service("favorQuotationItemService")
public class FavorQuotationItemServiceImpl extends GenericServiceImpl<FavorQuotationItem, String> implements FavorQuotationItemService {

    @Autowired
    private FavorQuotationItemDao favorQuotationItemDao;
    @Autowired
    private QuotationService quotationService;

    @Override
    protected GenericDao<FavorQuotationItem, String> getDao() {
        return favorQuotationItemDao;
    }

    @Override
    public void addToSpecifiedPosition(String quotationId, Integer indexNumber, User user) {
        FavorQuotationItem favorQuotationItem = get(Conditions.newInstance().eq("indexNumber", indexNumber));
        if (favorQuotationItem == null) {
            favorQuotationItem = new FavorQuotationItem();
            favorQuotationItem.setIndexNumber(indexNumber);
        }
        favorQuotationItem.setQuotationId(quotationId);
        favorQuotationItem.setUserId(user.getId());
        favorQuotationItem.setPasswordFlag(user.getRole());

        Quotation quotation = quotationService.getById(quotationId);
        favorQuotationItem.setCustomerName(quotation.getCustomerName());

        saveOrUpdate(favorQuotationItem);
    }

    @Override
    public void addToFront(String quotationId, User user) {
        PageHandler favorPage = getPage(
                new Query().addOrder(Order.asc("indexNumber"))
                        .setConditions(
                                Conditions.newInstance().eq("userId", user.getId())
                                        .eq("passwordFlag", user.getRole())
                        )
        );

        FavorQuotationItem newItem;

        //空位位置
        int number = 0;
        if (favorPage.getDataList().size() == 10) {
            number = 10;
            delete((((FavorQuotationItem) favorPage.getDataList().get(9)).getId()));
        } else {
            boolean flag = false;
            for (int i = 0; i < favorPage.getDataList().size(); i++){
                FavorQuotationItem f = (FavorQuotationItem) favorPage.getDataList().get(i);
                if (f.getIndexNumber() -1 > i) {
                    number = i;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                number = favorPage.getDataList().size();
            }
        }
        for (int j = 0; j < number; j++) {
            FavorQuotationItem f = (FavorQuotationItem) favorPage.getDataList().get(j);
            f.setIndexNumber(f.getIndexNumber() + 1);
            update(f);
        }

        newItem = new FavorQuotationItem();
        newItem.setIndexNumber(1);
        newItem.setUserId(user.getId());
        newItem.setPasswordFlag(user.getRole());

        Quotation quotation = quotationService.getById(quotationId);
        newItem.setCustomerName(quotation.getCustomerName());
        newItem.setQuotationId(quotationId);
        save(newItem);
    }
}
