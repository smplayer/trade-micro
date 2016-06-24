package com.as.erp.trade.micro.order.controller;

import com.as.erp.trade.micro.order.service.OrderProductItemService;
import com.as.erp.trade.micro.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 16-4-26.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductItemService orderProductItemService;

    @RequestMapping("order")
    public String order(
            @RequestParam(required = false) String id,
            ModelMap modelMap
    ) {

        return "order/order";
    }

}
