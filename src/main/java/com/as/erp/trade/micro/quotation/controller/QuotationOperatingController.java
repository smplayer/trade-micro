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
import com.as.erp.trade.micro.system.service.UserConfigItemService;
import com.as.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yrx on 2016/5/12.
 */
@Controller
public class QuotationOperatingController extends BaseQuotationController {

    public final static String DEFAULT_QUOTATION_ID = "default-quotation-id";

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FavorQuotationItemService favorQuotationItemService;
    @Autowired
    private UserConfigItemService userConfigItemService;

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
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(value = "factoryId", required = false) String factoryId,
            HttpSession session,
            ModelMap modelMap
    ) {
        if(!empty) {
            User user = (User) session.getAttribute("user");

            List<FavorQuotationItem> favorQuotationItemList = (List<FavorQuotationItem>) favorQuotationItemService.getPage(
                    new Query()
                            .setConditions(
                                    Conditions.newInstance()
                                            .eq("userId", user.getId()).eq("passwordFlag", user.getRole())
                            )
                            .addOrder(Order.asc("indexNumber"))
            ).getDataList();

//            if (!favorQuotationItemList.isEmpty()) {
//                modelMap.put("favorQuotationItemList", favorQuotationItemList);
//            }

            Quotation quotation = null;
            if (StringUtils.isBlank(id)) {
                id = userConfigItemService.getValue(user, DEFAULT_QUOTATION_ID);
            } else {
                userConfigItemService.setValue(user, DEFAULT_QUOTATION_ID, id);
            }
            if (StringUtils.isNotBlank(id)) {
                quotation = quotationService.getById(id);
            }

            if (quotation == null && !favorQuotationItemList.isEmpty()) {
                quotation = quotationService.getOperatingOrReloadFromArchive(favorQuotationItemList.get(0).getQuotationId());
            }

            if (quotation != null) {
                modelMap.put("quotation", quotation);

                PageHandler page = null;

                Conditions conditions = Conditions.newInstance().eq("quotationId", quotation.getId());
                if (StringUtils.isNotBlank(factoryId)) {
                    conditions.eq("factoryId", factoryId);
                }

                Query query = new Query()
                                .setPageIndex(pageIndex)
                                .setPageSize(pageSize)
                                .setConditions(conditions)
                                .addOrder(Order.desc("createdTime"));

                page = quotationProductItemDraftService.getPage(query);
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
            @RequestParam(value = "pageSize", defaultValue = "24") Integer pageSize,
            @RequestParam(value = "lang", required = false) String lang,
            ModelMap modelMap
    ) {
        Quotation quotation = quotationService.getById(id);
        PageHandler page = quotationProductItemDraftService.getPage(
                new Query().addOrder(Order.desc("createdTime"))
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

    @RequestMapping(value = "quotation/findFactoryForDraft")
    public String findFactory(
            @RequestParam("quotationProductItemDraftId") String quotationProductItemDraftId,
            @RequestParam("keywords") String factoryName,
            @RequestParam("linkman") String linkman,
            @RequestParam("contactNumber") String contactNumber,
            ModelMap modelMap
    ) throws UnsupportedEncodingException {
        modelMap.put("factoryName", factoryName);
        modelMap.put("linkman", linkman);
        modelMap.put("contactNumber", contactNumber);


        PageHandler page = factoryService.getPage(
                new Query().setConditions(
                        Conditions.newInstance()
                                .like("name", "%" + factoryName + "%")
                )
        );

        modelMap.put("quotationProductItemDraftId", quotationProductItemDraftId);

        if (page.getDataQuantity() > 0) {
            modelMap.put("page", page);
            modelMap.put("keywords", factoryName);
            return "factory/factory-search-for-draft";
        } else {
//            byte[] factoryNameBytes = factoryName.getBytes("ISO-8859-1");
//            factoryName = new String(factoryNameBytes, "UTF-8");
            return "factory/create";
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

    @RequestMapping("quotation/copyItem")
    public String copyItem(
            @RequestParam String quotationId,
            @RequestParam String id
    ) {

        return "redirect:/quotation/operating?id=" + quotationId;
    }


    @ResponseBody
    @RequestMapping("quotation/accumulativeTotal")
    public Object accumulativeTotal(
            @RequestBody Map<String, Object> req
    ) {
        String quotationId = (String) req.get("quotationId");
        Long pageQuantity = Long.valueOf((String) req.get("pageQuantity"));
        Long pageIndex = Long.valueOf((String) req.get("pageIndex"));
        Integer pageSize = Integer.valueOf((String) req.get("pageSize"));
        return quotationProductItemDraftService.getQuotationAccumulativeTotal(quotationId, pageQuantity, pageIndex, pageSize);
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
            User user = (User) session.getAttribute("user");
            Quotation archive = quotationService.saveToArchive(id);
            if (archive != null) {
                List<FavorQuotationItem> fs = favorQuotationItemService.getList(Conditions.newInstance().eq("quotationId", id));
                for (FavorQuotationItem f : fs) {
                    if (f != null) {
                        favorQuotationItemService.delete(f.getId());
                    }
                }
            }
            userConfigItemService.setValue(user, DEFAULT_QUOTATION_ID, null);
        }

        return "redirect:/quotation/operating";
    }

    @RequestMapping("quotation/reloadFromArchive")
    public String reloadFromArchive(
            @RequestParam("id") String id,
            HttpSession session,
            ModelMap modelMap
    ) {
        User user = (User) session.getAttribute("user");
        Quotation operating = quotationService.reloadFromArchive(id);
        favorQuotationItemService.addToFront(operating.getId(), user);

        modelMap.put("id", operating.getId());
        return "redirect:/quotation/operating";
    }


}
