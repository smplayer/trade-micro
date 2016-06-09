package com.as.erp.trade.micro.quotation.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.quotation.entity.FavorQuotationItem;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.service.FavorQuotationItemService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import com.as.user.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/5/27.
 */
@Controller
public class FavorQuotationItemController {

    @Autowired
    private FavorQuotationItemService favorQuotationItemService;
    @Autowired
    private QuotationService quotationService;

    @RequestMapping(value = "quotation/favor/setting", method = RequestMethod.GET)
    public String favorSetting(
            HttpSession session,
            ModelMap modelMap
    ) {
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        List<FavorQuotationItem> list = favorQuotationItemService.getList(
                new Query().setConditions(
                        Conditions.newInstance()
                        .eq("userId", user.getId())
                        .eq("passwordFlag", role)
                ).addOrder(Order.asc("indexNumber"))
        );

        PageHandler quotationPage = quotationService.getPage(

                new Query()
                        .setPageIndex(1L)
                        .setPageSize(Integer.MAX_VALUE)
                        .setConditions(
                        Conditions.newInstance()
                                .eq("operationFlag", Quotation.FLAG_OPERATING)
                ).addOrder(Order.desc("startedDate"))
        );

        modelMap.put("list", list);
        modelMap.put("quotationPage", quotationPage);

        return "quotation/favor-quotation-setting";
    }

    @RequestMapping(value = "quotation/favor/setting", method = RequestMethod.POST)
    public String favorSetting(
            @RequestParam Integer indexNumber,
            @RequestParam String quotationId,
            HttpSession session,
            ModelMap modelMap
    ) {

        FavorQuotationItem existsItem = favorQuotationItemService.get(Conditions.newInstance().eq("quotationId", quotationId));
        if (existsItem != null)
            favorQuotationItemService.delete(existsItem.getId());

        FavorQuotationItem item = favorQuotationItemService.get(
                Conditions.newInstance()
                .eq("indexNumber",indexNumber)
        );
        Quotation quotation = quotationService.getById(quotationId);
        if (item == null) {
            User user = (User) session.getAttribute("user");
            String role = user.getRole();

            item = new FavorQuotationItem();
            item.setIndexNumber(indexNumber);
            item.setUserId(user.getId());
            item.setPasswordFlag(role);
        }

        item.setCustomerName(quotation.getCustomerName());
        item.setQuotationId(quotationId);
        favorQuotationItemService.saveOrUpdate(item);

        return "quotation/favor-quotation-setting-success";

    }

    @RequestMapping(value = "quotation/favor/delete", method = RequestMethod.POST)
    public String deleteFavor(
            @RequestParam Integer indexNumber,
            HttpSession session,
            ModelMap modelMap
    ) {

        FavorQuotationItem item = favorQuotationItemService.get(
                Conditions.newInstance()
                .eq("indexNumber",indexNumber)
        );
        if (item != null) {
            favorQuotationItemService.delete(item.getId());
        }

        return "redirect:/quotation/favor/setting";

    }

    @ResponseBody
    @RequestMapping("quotation/favor/list")
    public Object favorList(
//            ModelMap modelMap,
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        PageHandler page = favorQuotationItemService.getPage(
                new Query().addOrder(Order.asc("indexNumber"))
                .setConditions(
                        Conditions.newInstance().eq("userId", user.getId())
                        .eq("passwordFlag", user.getRole())
                )
        );
//        modelMap.put("page", page);
        return page.getDataList();
    }
}