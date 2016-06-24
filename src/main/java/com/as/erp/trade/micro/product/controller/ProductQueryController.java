package com.as.erp.trade.micro.product.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
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
    @Autowired
    private FactoryService factoryService;

    @RequestMapping("product")
    public String get(
            @PathVariable("id") String id,
            ModelMap modelMap
    ) {
        Product product = productService.getById(id);
        modelMap.put("product", product);
        return "product/details";
    }

    @RequestMapping("product/listOfFactory")
    public String list(
            @RequestParam(value = "factoryId") String factoryId,
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "keywords", required = false) String keywords,
            ModelMap modelMap
    ) {
        Factory factory = factoryService.getById(factoryId);
        modelMap.put("factory", factory);

        Conditions conditions = Conditions.newInstance().eq("factoryId", factoryId);

        Query query = new Query();
        query.setPageIndex(pageIndex)
                .setPageSize(pageSize)
                .addOrder(Order.desc("addedDate"))
                .setConditions(conditions);

        keywords = StringUtils.trim(keywords);
        if (StringUtils.isNotBlank(keywords)) {
            conditions.or(
                    Conditions.newInstance()
                        .like("name", "%" + keywords + "%")
                        .like("factoryProductName", "%" + keywords + "%")
                        .like("companyProductName", "%" + keywords + "%")
                        .like("factoryProductNo", "%" + keywords + "%")
                        .like("companyProductNo", "%" + keywords + "%")
            );
        }

        PageHandler productPage = productService.getPage(query);
        modelMap.put("productPage", productPage);
        return "product/listOfFactory";
    }

    @RequestMapping("product/list")
    public String list(
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "keywords", required = false) String keywords,
            ModelMap modelMap
    ) {

        Query query = new Query();
        query.setPageIndex(pageIndex)
                .setPageSize(pageSize)
                .addOrder(Order.desc("addedDate"));

        if (StringUtils.isNotBlank(keywords)) {
            query.setConditions(
                    Conditions.newInstance().or(
                            Conditions.newInstance()
                                    .like("name", "%" + keywords + "%")
                                    .like("factoryProductName", "%" + keywords + "%")
                                    .like("companyProductName", "%" + keywords + "%")
                                    .like("factoryProductNo", "%" + keywords + "%")
                                    .like("companyProductNo", "%" + keywords + "%")
                    )
            );
        }

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
                .setConditions(Conditions.newInstance().eq("productStatus", Product.PRODUCT_STATUS_COMPLETE))
                .addOrder(Order.desc("addedDate"));

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
                )
                .addOrder(Order.desc("addedDate"));

        PageHandler productPage = productService.getPage(query);
        modelMap.put("productPage", productPage);
        modelMap.put("title", "补料产品");
        return "product/list";
    }

}
