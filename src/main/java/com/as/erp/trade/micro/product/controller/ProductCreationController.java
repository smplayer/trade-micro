package com.as.erp.trade.micro.product.controller;

import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 16-4-26.
 */
@Controller
public class ProductCreationController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "product/create", method = RequestMethod.GET)
    public String create() {
        return "product/edit";
    }

    @RequestMapping(value = "product/create", method = RequestMethod.POST)
    public String create(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "factory-product-no", required = false) String factoryProductNo,
            @RequestParam(value = "company-product-no", required = false) String companyProductNo,
            @RequestParam(value = "factory-price", required = false) Double factoryPrice,
            @RequestParam(value = "carton-size", required = false) String cartonSize,
            @RequestParam(value = "packing-quantity", required = false) Integer packingQuantity,
            @RequestParam(value = "gross-weight", required = false) Double grossWeight,
            @RequestParam(value = "net-weight", required = false) Double netWeight,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestParam(value = "factory-id", required = false) String factoryId,
            @RequestParam(value = "package-form", required = false) String packageForm,
            @RequestParam(value = "function-description", required = false) String functionDescription,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "sub-category", required = false) String subCategory
    ) {
        Product p = new Product();
        p.setName(name);
        p.setFactoryProductNo(factoryProductNo);
        p.setCompanyProductNo(companyProductNo);
        p.setFactoryPrice(factoryPrice);
        p.setCartonSize(cartonSize);
        p.setPackingQuantity(packingQuantity);
        p.setGrossWeight(grossWeight);
        p.setNetWeight(netWeight);
        p.setRemark(remark);
        p.setFactoryId(factoryId);
        p.setPackageForm(packageForm);
        p.setFunctionDescription(functionDescription);
        p.setCategory(category);
        p.setSubCategory(subCategory);
        p.setAddedDate(new Date());
        productService.save(p);

        return "redirect:/product/create";
    }

    @ResponseBody
    @RequestMapping(value = "ajax/product/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(
            @RequestBody Map<String, Object> req
    ) {
        Product p;

        String productId = (String) req.get("id");
        if (StringUtils.isNotBlank(productId)) {
            p = productService.getById(productId);
        } else {
            p = new Product();
            p.setAddedDate(new Date());
        }

        p.setFactoryName((String) req.get("factoryName"));
        p.setCompanyProductName((String) req.get("companyProductName"));
        p.setFactoryProductName((String) req.get("factoryProductName"));
        p.setFactoryProductNo((String) req.get("factoryProductNo"));
        p.setCompanyProductNo((String) req.get("companyProductNo"));
        p.setFactoryPrice(Double.valueOf( StringUtils.isNotBlank((String)req.get("factoryPrice")) ? (String) req.get("factoryPrice") : "0") );
        p.setCartonSize((String) req.get("cartonSize"));
        p.setPackingQuantity(Integer.valueOf( StringUtils.isNotBlank((String)req.get("packingQuantity")) ? (String) req.get("packingQuantity") : "0") );
        p.setGrossWeight(Double.valueOf( StringUtils.isNotBlank((String)req.get("grossWeight")) ? (String) req.get("grossWeight") : "0") );
        p.setNetWeight(Double.valueOf( StringUtils.isNotBlank((String)req.get("netWeight")) ? (String) req.get("netWeight") : "0") );
        p.setRemark((String) req.get("remark"));
        p.setFactoryId((String) req.get("factoryId"));
        p.setPackageForm((String) req.get("packageForm"));
        p.setFunctionDescription((String) req.get("functionDescription"));
        p.setCategory((String) req.get("category"));
        p.setSubCategory((String) req.get("subCategory"));

        if (p.getId() != null)
            productService.update(p);
        else
            productService.save(p);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }


}
