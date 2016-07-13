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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void addToSpecifiedPosition(String quotationId, Integer indexNumber, User user, boolean move) {
        FavorQuotationItem exists = get(Conditions.newInstance().eq("quotationId", quotationId).eq("userId", user.getId()).eq("passwordFlag", user.getRole()));
        if (exists != null) {
            delete(exists.getId());
        }

        FavorQuotationItem favorQuotationItem = get(
                Conditions.newInstance().eq("indexNumber", indexNumber)
                .eq("userId", user.getId())
                .eq("passwordFlag", user.getRole())
        );
        if (favorQuotationItem == null) {
            favorQuotationItem = new FavorQuotationItem();
            favorQuotationItem.setIndexNumber(indexNumber);
        } else {
            if (move) {
                moveOutPosition(indexNumber, user);
                favorQuotationItem = new FavorQuotationItem();
                favorQuotationItem.setIndexNumber(indexNumber);
            }
        }
        favorQuotationItem.setQuotationId(quotationId);
        favorQuotationItem.setUserId(user.getId());
        favorQuotationItem.setPasswordFlag(user.getRole());

        Quotation quotation = quotationService.getById(quotationId);
        favorQuotationItem.setCustomerName(quotation.getCustomerName());

        saveOrUpdate(favorQuotationItem);
    }

    private void moveOutPosition(Integer position, User user) {
        List<FavorQuotationItem> itemList = getList( Conditions.newInstance().eq("userId", user.getId()).eq("passwordFlag", user.getRole()) );

        Map<Integer, FavorQuotationItem> itemMap = new HashMap<>();
        for (FavorQuotationItem item : itemList) {
            itemMap.put(item.getIndexNumber(), item);
        }
        if (itemMap.get(position) != null) {
            Integer emptyPosition = null;
            for (int i=position; i <= 10; i++) {
                if (itemMap.get(i) == null) {
                    emptyPosition = i;
                    break;
                }
            }
            if (emptyPosition == null) {
                emptyPosition = 10;
                delete(itemMap.get(10).getId());
            }
            for (int i = position; i < emptyPosition; i++) {
                FavorQuotationItem item = itemMap.get(i);
                item.setIndexNumber(item.getIndexNumber() + 1);
                update(item);
            }
        }
    }

    @Override
    public void addToFront(String quotationId, User user) {
//        PageHandler favorPage = getPage(
//                new Query().addOrder(Order.asc("indexNumber"))
//                        .setConditions(
//                                Conditions.newInstance().eq("userId", user.getId())
//                                        .eq("passwordFlag", user.getRole())
//                        )
//        );
//
//        FavorQuotationItem newItem;

        //空位位置
//        int number = 0;
//        boolean full = false;
//        boolean empty = false;
//
//        if (favorPage.getDataList().size() == 10) {
//            number = 10;
//            full = true;
//        }else if (favorPage.getDataList().size() == 0) {
//            empty = true;
//        }
//
//        boolean atLast = true;
//        if (!empty){
//            for (int i = 0; i < favorPage.getDataList().size(); i++){
//                FavorQuotationItem f = (FavorQuotationItem) favorPage.getDataList().get(i);
//                number = i;
//                if (f.getIndexNumber() -1 > i) {
//                    atLast = false;
//                    break;
//                }
//            }
//        }
//
//        if (!empty && atLast) {
//            number++;
//        }
//
//        for (int j = 0; j < number; j++) {
//            FavorQuotationItem f = (FavorQuotationItem) favorPage.getDataList().get(j);
//            f.setIndexNumber(f.getIndexNumber() + 1);
//            update(f);
//        }
//
//        if (full) {
//            delete((((FavorQuotationItem) favorPage.getDataList().get(9)).getId()));
//        }

//        newItem = new FavorQuotationItem();
//        newItem.setIndexNumber(1);
//        newItem.setUserId(user.getId());
//        newItem.setPasswordFlag(user.getRole());
//
//        Quotation quotation = quotationService.getById(quotationId);
//        newItem.setCustomerName(quotation.getCustomerName());
//        newItem.setQuotationId(quotationId);
//        save(newItem);
        addToSpecifiedPosition(quotationId, 1, user, true);
    }
}
