package com.as.erp.trade.micro.quotation.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.factory.service.FactoryService;
import com.as.erp.trade.micro.product.service.ProductService;
import com.as.erp.trade.micro.quotation.entity.FavorQuotationItem;
import com.as.erp.trade.micro.quotation.entity.Quotation;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.FavorQuotationItemService;
import com.as.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/5/12.
 */
@Controller
public class QuotationOperatingController extends BaseQuotationController {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FavorQuotationItemService favorQuotationItemService;

    @RequestMapping("quotation/operating/list")
    public String operatingList(
            ModelMap modelMap
    ) {
        List<Quotation> list = quotationService.getList(
                Conditions.newInstance()
                        .eq("operationFlag", Quotation.FLAG_OPERATING)
        );
        modelMap.put("list", list);
        return "quotation/operating-list";
    }

    @RequestMapping("quotation/operating")
    public String operating(
            @RequestParam(value = "empty", required = false, defaultValue = "false") Boolean empty,
//            @RequestParam(value = "indexNumber", required = false) Integer indexNumber,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "pageIndex", required = false) Long pageIndex,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "factoryId", required = false) String factoryId,
            HttpSession session,
            ModelMap modelMap
    ) {
        if(!empty) {
            User user = (User) session.getAttribute("user");

            List<FavorQuotationItem> favorQuotationItemList = (List<FavorQuotationItem>) favorQuotationItemService.getPage(
                    new Query().setConditions(
                            Conditions.newInstance().eq("userId", user.getId()).eq("passwordFlag", user.getRole())
                    ).addOrder(Order.asc("indexNumber"))
            ).getDataList();

            if (!favorQuotationItemList.isEmpty()) {
                modelMap.put("favorQuotationItemList", favorQuotationItemList);
            }

            Quotation quotation = null;
            if (StringUtils.isNotBlank(id)) {
                quotation = quotationService.getById(id);
            } else {
                if (!favorQuotationItemList.isEmpty()) {
                    quotation = quotationService.getOperatingOrReloadFromArchive(favorQuotationItemList.get(0).getQuotationId());
                }
            }

            if (quotation != null) {
                modelMap.put("quotation", quotation);

                PageHandler page = null;

                Conditions conditions = Conditions.newInstance().eq("quotationId", quotation.getId());
                if (StringUtils.isNotBlank(factoryId)) {
                    conditions.eq("factoryId", factoryId);
                }

                page = quotationProductItemDraftService.getPage(
                        new Query()
                                .setPageIndex(pageIndex)
                                .setPageSize(pageSize)
                                .setConditions(conditions)
                                .addOrder(Order.desc("addedDate"))
                );
                modelMap.put("page", page);
            }
        }
        return "quotation/draft";
    }

    @RequestMapping("quotation/confirming/order")
    public String confirmingOrder(
            @RequestParam(required = false, defaultValue = "company") String productNoFrom,
            @RequestParam("id") String id,
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "lang", required = false) String lang,
            ModelMap modelMap
    ) {
        Quotation quotation = quotationService.getById(id);
        PageHandler page = quotationProductItemDraftService.getPage(
                new Query().addOrder(Order.desc("addedDate"))
                        .setPageIndex(pageIndex)
                        .setPageSize(pageSize)
                        .setConditions(
                                Conditions.newInstance()
                                        .eq("quotationId", id)
                        )
        );
        modelMap.put("productNoFrom", productNoFrom);
        modelMap.put("quotation", quotation);
        modelMap.put("page", page);

        modelMap.put("lang", lang);
        if ("en".equals(lang)) {
            return "quotation/confirming-order-en";
        }
        return "quotation/confirming-order";
    }

    @ResponseBody
    @RequestMapping("quotation/accumulativeTotal")
    public Object accumulativeTotal(
            @RequestBody Map<String, Object> req
    ) {
        String quotationId = (String) req.get("quotationId");
        Long pageIndex = Long.valueOf((String) req.get("pageIndex"));
        Integer pageSize = Integer.valueOf((String) req.get("pageSize"));
        return quotationProductItemDraftService.getQuotationAccumulativeTotal(quotationId, pageIndex, pageSize);
    }

    @RequestMapping("quotation/saveToArchive")
    public String saveToArchive(
            @RequestParam("id") String id,
            HttpSession session
    ) {
//        if (StringUtils.isBlank(id)) {
//            User user = (User) session.getAttribute("user");
//            List<FavorQuotationItem> favorQuotationItemList = favorQuotationItemService.getList(
//                    new Query().addOrder(Order.asc("indexNumber"))
//                            .setPageSize(1)
//                            .setConditions(
//                                Conditions.newInstance()
//                                    .eq("userId", user.getId())
//                                    .eq("passwordFlag", user.getRole())
//                            )
//            );
//            if (favorQuotationItemList.size() == 1) {
//                id = favorQuotationItemList.get(0).getQuotationId();
//            }
//        }


        if (StringUtils.isNotBlank(id)) {
            Quotation quotation = quotationService.saveToArchive(id);
            FavorQuotationItem f = favorQuotationItemService.get(Conditions.newInstance().eq("quotationId", quotation.getId()));
            favorQuotationItemService.delete(f.getId());
        }

        return "redirect:/quotation/operating";
    }

    @RequestMapping("quotation/reloadFromArchive")
    public String reloadFromArchive(
            @RequestParam("id") String id,
            ModelMap modelMap
    ) {
        Quotation operating = quotationService.reloadFromArchive(id);
        modelMap.put("id", operating.getId());
        return "redirect:/quotation/operating";
    }

    @RequestMapping(value = "quotation/findFactoryForDraft", method = RequestMethod.GET)
    public String findFactory(
            @RequestParam("quotationProductItemDraftId") String quotationProductItemDraftId,
            @RequestParam("keywords") String factoryName,
            ModelMap modelMap
    ) {
        PageHandler page = factoryService.getPage(
                new Query().setConditions(
                        Conditions.newInstance()
                                .like("name", "%" + factoryName + "%")
                )
        );

        modelMap.put("quotationProductItemDraftId", quotationProductItemDraftId);

        if (page.getDataQuantity() > 0) {
            modelMap.put("page", page);
            return "factory/factory-search-for-draft";
        } else {
            modelMap.put("factoryName", factoryName);
            return "redirect:/factory/create";
        }

    }

    @ResponseBody
    @RequestMapping(value = "quotation/selectFactoryForProductItemDraft", method = RequestMethod.POST)
    public Object selectFactoryForProductItemDraft(
            @RequestBody Map<String, Object> req
    ) {
        String id = (String) req.get("id");
        String factoryId = (String) req.get("factoryId");
        quotationProductItemDraftService.selectFactoryForProductItemDraft(id, factoryId);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @ResponseBody
    @RequestMapping("quotation/generateProducts")
    public Object generateProducts(
            @RequestBody Map<String, Object> req
    ) {
        List<String> ids = (List<String>) req.get("ids");
        quotationProductItemDraftService.generateProducts(ids);

        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        map.put("result", "success");
        return map;
    }

    @ResponseBody
    @RequestMapping("quotation/deleteDraftItem")
    public Object deleteDraftItem(
            @RequestBody Map<String, Object> req
    ) {
        List<String> ids = (List<String>) req.get("ids");
        quotationProductItemDraftService.delete(ids);

        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @ResponseBody
    @RequestMapping("quotation/saveRemark")
    public Object saveRemark(
            @RequestBody Map<String, Object> req
    ) {
        List<Map<String, String>> remarkData = (List<Map<String, String>>) req.get("remarkData");

        for (Map<String, String> r : remarkData) {
            QuotationProductItemDraft draft = quotationProductItemDraftService.getById(r.get("id"));
            draft.setRemark(r.get("remark"));
            quotationProductItemDraftService.update(draft);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

}
