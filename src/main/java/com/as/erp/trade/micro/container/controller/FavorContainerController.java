package com.as.erp.trade.micro.container.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.container.entity.Container;
import com.as.erp.trade.micro.container.service.ContainerService;
import com.as.erp.trade.micro.order.service.FavorCustomerService;
import com.as.erp.trade.micro.order.service.OrderProductItemService;
import com.as.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-4-26.
 */
@Controller
public class FavorContainerController {

    @Autowired
    private FavorCustomerService favorCustomerService;
    @Autowired
    private OrderProductItemService orderProductItemService;
    @Autowired
    private ContainerService containerService;

    @ResponseBody
    @RequestMapping("container/favor/list")
    public Object favorList(
            @RequestBody Map<String, Object> req,
            HttpSession session
    ){

        User user = (User) session.getAttribute("user");

        String sheetId = (String) req.get("sheetId");

        List<Container> favorList = containerService.getList(
                new Query().addOrder(Order.asc("createdTime"))
                        .setConditions(
                                Conditions.newInstance()
                                .eq("sheetId", sheetId)
//                                .eq("userId", user.getId())
//                                .eq("passwordFlag", user.getRole())
                        )
        );

        return favorList;
    }




    @RequestMapping(value = "container/favor/setting",method = RequestMethod.GET)
    public String setFavor(
            @RequestParam String sheetId,
            HttpSession session,
            ModelMap modelMap
    ) {

        User user = (User) session.getAttribute("user");
        List<Container> sortedList = containerService.getList(
                new Query().setConditions(
                        Conditions.newInstance()
                                .neOrIsNotNull("indexNumber", 0)
                                .eq("sheetId", sheetId)
//                        .eq("userId", user.getId())
//                        .eq("passwordFlag", user.getRole())
                ).addOrder(Order.asc("indexNumber"))
        );

        List<Container> list = containerService.getList(
                new Query().setConditions(
                        Conditions.newInstance()
                                .eq("sheetId", sheetId)
                ).addOrder(Order.asc("createdTime"))
        );

//        modelMap.put("sortedList", sortedList);
        modelMap.put("list", list);

        return "container/favor-container-setting";
    }

    @RequestMapping(value = "container/favor/setting", method = RequestMethod.POST)
    public String setFavor(
            @RequestParam(required = true) String sheetId,
            @RequestParam(required = true) Integer indexNumber,
            @RequestParam(required = true) String containerId,
            HttpSession session,
            ModelMap modelMap
    ){
        User user = (User) session.getAttribute("user");
        if (indexNumber != null && StringUtils.isNotBlank(containerId)) {

            Container oldItem = containerService.get(
                    Conditions.newInstance()
                            .eq("sheetId", sheetId)
                            .eq("indexNumber", indexNumber)
//                            .eq("userId", user.getId())
//                            .eq("passwordFlag", user.getRole())
            );
            if (oldItem != null){
                oldItem.setIndexNumber(null);
                containerService.update(oldItem);
            }

            Container newItem = containerService.getById(containerId);
            newItem.setIndexNumber(indexNumber);
            containerService.update(newItem);
        }

        return "container/favor-container-setting-success";
    }

}
