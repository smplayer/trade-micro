package com.as.erp.trade.micro.product.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.factory.service.FactoryService;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yrx on 2016/5/26.
 */
@Controller
public class ProductFactorySelectionController {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "product/findFactoryForProduct")
    public String findFactory(
            @RequestParam("factoryName") String factoryName,
            ModelMap modelMap
    ){
        PageHandler page = factoryService.getPage(
                new Query().setConditions(
                        Conditions.newInstance()
                                .like("name", "%" + factoryName + "%")
                )
        );

        modelMap.put("page", page);
        return "factory/factory-search-for-product";
    }

//    @ResponseBody
//    @RequestMapping(value = "quotation/selectFactoryForProduct", method = RequestMethod.POST)
//    public Object selectFactoryForProductItemDraft(
//            @RequestBody Map<String, Object> req
//    ) {
//        String id = (String) req.get("id");
//        String factoryId = (String) req.get("factoryId");
//        Product product = productService.selectFactory(id, factoryId);
//        Map<String, Object> map = new HashMap<>();
//        map.put("product", product);
//        map.put("result", "success");
//        return map;
//    }

}
