package com.as.erp.trade.micro.quotation.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.quotation.entity.FavorQuotationItem;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.service.FavorQuotationItemService;
import com.as.erp.trade.micro.quotation.service.QuotationService;
import com.as.user.entity.User;
import org.apache.commons.lang3.StringUtils;
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
                ).addOrder(Order.asc("createdTime"))
        );

        modelMap.put("list", list);
        modelMap.put("quotationPage", quotationPage);

        return "quotation/favor-quotation-setting";
    }

    @RequestMapping(value = "quotation/favor/setting", method = RequestMethod.POST)
    public String favorSetting(
            @RequestParam(required = false) Integer indexNumber,
            @RequestParam(required = false) String quotationId,
            HttpSession session,
            ModelMap modelMap
    ) {
        User user = (User) session.getAttribute("user");
        if (indexNumber != null && StringUtils.isNotBlank(quotationId)) {
            favorQuotationItemService.addToSpecifiedPosition(quotationId, indexNumber, user, false);

//            User standard = new User();
//            standard.setUsername(user.getUsername());
//            standard.setRole(User.ROLE_STANDARD);
//            favorQuotationItemService.addToSpecifiedPosition(quotationId, indexNumber, standard, true);
        }

        return "quotation/favor-quotation-setting-success";
    }

    @RequestMapping(value = "quotation/favor/delete", method = RequestMethod.POST)
    public String deleteFavor(
            @RequestParam(required = false) Integer[] indexNumber,
            @RequestParam(required = false) String[] quotationId,
            HttpSession session,
            ModelMap modelMap
    ) {

        if (indexNumber == null) {
            indexNumber = new Integer[]{};
        }
        if (quotationId == null) {
            quotationId = new String[]{};
        }
        for (Integer i = 0 ; i < indexNumber.length ; i++) {
            List<FavorQuotationItem> items = favorQuotationItemService.getList(
                    Conditions.newInstance()
                            .eq("indexNumber", indexNumber[i])
            );
            for (FavorQuotationItem item : items) {
                if (item != null) {
                    favorQuotationItemService.delete(item.getId());
                }
            }

        }
        for (Integer i = 0 ; i < quotationId.length ; i++) {

            String qid = quotationId[i];
            if (StringUtils.isNotBlank(qid)) {
                quotationService.delete(qid);
                FavorQuotationItem item = favorQuotationItemService.get(
                        Conditions.newInstance()
                                .eq("quotationId", qid)
                );
                if (item != null) {
                    favorQuotationItemService.delete(item.getId());
                }
            }

        }

        return "quotation/favor-quotation-setting-success";
    }

    private void deleteFavorByIndexNumber(Integer i) {
        FavorQuotationItem item = favorQuotationItemService.get(
                Conditions.newInstance()
                        .eq("indexNumber", i)
        );
        if (item != null) {
            favorQuotationItemService.delete(item.getId());
        }
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
