package com.as.erp.trade.micro.product.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 16-4-27.
 */
@Controller
public class ProductQueryController {

    @Autowired
    private ProductService productService;

    @RequestMapping("product/{id}")
    public String get(
            @PathVariable("id") String id,
            ModelMap modelMap
    ){
        Product product = productService.getById(id);
        modelMap.put("product", product);
        return "product/details";
    }

    @RequestMapping("product/list")
    public String list(
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            ModelMap modelMap
    ){

        Query query = new Query();
        query.setPageIndex(pageIndex)
                .setPageSize(pageSize);

        PageHandler productPage = productService.getPage(query);
        modelMap.put("productPage", productPage);
        return "product/list";
    }

}
