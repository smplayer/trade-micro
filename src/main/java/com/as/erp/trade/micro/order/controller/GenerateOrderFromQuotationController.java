package com.as.erp.trade.micro.order.controller;

import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.order.entity.FavorCustomer;
import com.as.erp.trade.micro.order.entity.OrderProductItem;
import com.as.erp.trade.micro.order.service.FavorCustomerService;
import com.as.erp.trade.micro.order.service.OrderProductItemService;
import com.as.erp.trade.micro.order.service.OrderService;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import com.as.user.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/6/25.
 */
@Controller
public class GenerateOrderFromQuotationController {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private QuotationProductItemDraftService quotationProductItemDraftService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductItemService orderProductItemService;

    @Autowired
    private FavorCustomerService favorCustomerService;

    @ResponseBody
    @RequestMapping("order/generateOrderFromQuotation")
    public Object generateOrderFromQuotation(
            HttpSession session,
            @RequestBody Map<String, Object> req
    ) {
        User user = (User) session.getAttribute("user");
        String quotationId = (String) req.get("quotationId");

        Quotation quotation = quotationService.getById(quotationId);
        List<QuotationProductItemDraft> draftList = quotationProductItemDraftService.getList(
                new Query().setConditions(
                        Conditions.newInstance().eq("quotationId", quotationId)
                ).addOrder(Order.asc("createdTime"))
        );

        com.as.erp.trade.micro.order.entity.Order order = orderService.get(Conditions.newInstance().eq("quotationId", quotationId));

        if (order == null) {
            order = new com.as.erp.trade.micro.order.entity.Order();
            order.setQuotationId(quotationId);
        } else {
            List<OrderProductItem> orderProductItems = orderProductItemService.getList(Conditions.newInstance().eq("orderId", order.getId()));
            for (OrderProductItem item : orderProductItems) {
                orderProductItemService.delete(item.getId());
            }
        }

        order.setCustomerName(quotation.getCustomerName());
        orderService.saveOrUpdate(order);

        for (QuotationProductItemDraft draft : draftList) {
            OrderProductItem opi = new OrderProductItem();
            opi.setOrderId(order.getId());
            opi.setProductId(draft.getProductId());
            opi.setFactoryId(draft.getFactoryId());
            opi.setFactoryName(draft.getFactoryName());
            opi.setLinkman(draft.getLinkman());
            opi.setContactNumber(draft.getContactNumber());
            opi.setImageURL(draft.getImageURL());
            opi.setCompanyProductName(draft.getCompanyProductName());
            opi.setCompanyProductNo(draft.getCompanyProductNo());
            opi.setFunctionDescription(draft.getFunctionDescription());
            opi.setPackageForm(draft.getPackageForm());
            opi.setUnit(draft.getUnit());
            opi.setFactoryPrice(draft.getFactoryPrice());
            opi.setCartonSize(draft.getCartonSize());
            opi.setPackingQuantity(draft.getPackingQuantity());
            opi.setGrossWeight(draft.getGrossWeight());
            opi.setNetWeight(draft.getNetWeight());
            opi.setOrderedCartonQuantity(draft.getOrderedCartonQuantity());
            opi.setVolume(draft.getTotalVolume());
            opi.setPayment(draft.getTotalAmount());
            opi.setDeliveredCartonQuantity(null);
            opi.setRemainingCartonQuantity(null);
            opi.setScheduledDeliverableCartonQuantity(null);
            opi.setScheduledDeliverableVolume(null);
            opi.setScheduledDeliverablePayment(null);
            opi.setAddedDate(new Date());

            opi.setRemainingCartonQuantity(opi.getOrderedCartonQuantity());

            orderProductItemService.save(opi);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        FavorCustomer favorCustomer = favorCustomerService.get(
                Conditions.newInstance()
                        .eq("customerName", order.getCustomerName())
//                        .eq("userId", user.getId())
//                        .eq("passwordFlag", user.getRole())
        );

        if (favorCustomer == null) {
            List<FavorCustomer> favorCustomers = favorCustomerService.getList(Conditions.newInstance().neOrIsNotNull("indexNumber", 0));

            Map<Integer, FavorCustomer> favorCustomerMap = new HashMap<>();
            for (FavorCustomer favorCustomer2 : favorCustomers) {
                favorCustomerMap.put(favorCustomer2.getIndexNumber(), favorCustomer2);
            }

            int availableIndexNumber = Integer.MAX_VALUE;
            for (int i = 1; i <= 10; i ++) {
                boolean flag = true;

                if (favorCustomerMap.get(i) == null) {
                    availableIndexNumber = i;
                    break;
                }
            }

            favorCustomer = new FavorCustomer();
            if (availableIndexNumber <= 10) {
                favorCustomer.setIndexNumber(availableIndexNumber);
            }
            favorCustomer.setCustomerName(order.getCustomerName());
            favorCustomer.setUserId(user.getId());
            favorCustomer.setPasswordFlag(user.getRole());
            favorCustomerService.save(favorCustomer);
        }


        quotation.setGeneratedOrder(true);
        quotationService.update(quotation);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

}
