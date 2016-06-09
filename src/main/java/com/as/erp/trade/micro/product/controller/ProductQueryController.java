package com.as.erp.trade.micro.product.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
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

    @RequestMapping("product")
    public String get(
            @PathVariable("id") String id,
            ModelMap modelMap
    ) {
        Product product = productService.getById(id);
        modelMap.put("product", product);
        return "product/details";
    }

    @RequestMapping("product/list")
    public String list(
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            ModelMap modelMap
    ) {

        Query query = new Query();
        query.setPageIndex(pageIndex)
                .setPageSize(pageSize);

        PageHandler productPage = productService.getPage(query);
        modelMap.put("productPage", productPage);
        return "product/list";
    }

    @RequestMapping("product/list/complete")
    public String completeList(
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            ModelMap modelMap
    ) {

        Query query = new Query().setPageIndex(pageIndex)
                .setPageSize(pageSize)
                .setConditions(Conditions.newInstance().eq("productStatus", Product.PRODUCT_STATUS_COMPLETE));

        PageHandler productPage = productService.getPage(query);
        modelMap.put("productPage", productPage);
        modelMap.put("title", "标准产品");
        return "product/list";
    }

    @RequestMapping("product/list/incomplete")
    public String incompleteList(
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            ModelMap modelMap
    ) {

        Query query = new Query().setPageIndex(pageIndex)
                .setPageSize(pageSize)
                .setConditions(
                        Conditions.newInstance().or(
                                Conditions.newInstance()
                                        .ne("productStatus", Product.PRODUCT_STATUS_COMPLETE).isNull("productStatus")
                        )
                );

        PageHandler productPage = productService.getPage(query);
        modelMap.put("productPage", productPage);
        modelMap.put("title", "补料产品");
        return "product/list";
    }

}
