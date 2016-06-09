package com.as.erp.trade.micro.product.controller;

import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-4-27.
 */
@Controller
public class ProductModificationController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "product/modify/{id}", method = RequestMethod.GET)
    public String modify(
            @PathVariable("id") String id,
            ModelMap modelMap
    ){
        Product p = productService.getById(id);
        modelMap.put("product", p);
        return "product/edit";
    }

    @RequestMapping(value = "product/modify", method = RequestMethod.POST)
    public String modify(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "factory-product-no") String factoryProductNo,
            @RequestParam(value = "company-product-no") String companyProductNo,
            @RequestParam(value = "factory-price") Double factoryPrice,
            @RequestParam(value = "carton-size") String cartonSize,
            @RequestParam(value = "packing-quantity") Integer packingQuantity,
            @RequestParam(value = "gross-weight") Double grossWeight,
            @RequestParam(value = "net-weight") Double netWeight,
            @RequestParam(value = "remark") String remark,
            @RequestParam(value = "factory-id") String factoryId,
            @RequestParam(value = "package-form") String packageForm,
            @RequestParam(value = "function-description") String functionDescription,
            @RequestParam(value = "category") String category,
            @RequestParam(value = "sub-category") String subCategory
    ){

        Product p = productService.getById(id);
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
        productService.update(p);

        return "redirect:/product/" + "id";
    }

    @ResponseBody
    @RequestMapping(value = "ajax/product/modify", method = RequestMethod.POST)
    public Object ajaxModify(
            @RequestBody List<Map> req
    ){
        for (Map pMap : req){
            Product p = productService.getById((String) pMap.get("id"));
            p.setName((String) pMap.get("name"));
            p.setCompanyProductName((String) pMap.get("companyProductName"));
//            p.setFactoryName((String) pMap.get("factoryName"));
            p.setFactoryProductNo((String) pMap.get("factoryProductNo"));
            p.setCompanyProductNo((String) pMap.get("companyProductNo"));
            p.setFactoryPrice(StringUtils.isNotBlank((CharSequence) pMap.get("factoryPrice")) ? Double.valueOf((String) pMap.get("factoryPrice")) : null);
            p.setCartonSize((String) pMap.get("cartonSize"));
            p.setPackingQuantity(StringUtils.isNotBlank((CharSequence) pMap.get("packingQuantity")) ? Integer.valueOf((String) pMap.get("packingQuantity")) : null);
            p.setGrossWeight(StringUtils.isNotBlank((CharSequence) pMap.get("grossWeight")) ? Double.valueOf((String) pMap.get("grossWeight")) : null);
            p.setNetWeight(StringUtils.isNotBlank((CharSequence) pMap.get("netWeight")) ? Double.valueOf((String) pMap.get("netWeight")) : null);
//            p.setGrossWeight(Double.valueOf((String) pMap.get("grossWeight")));
//            p.setNetWeight(Double.valueOf((String) pMap.get("netWeight")));
            p.setRemark((String) pMap.get("remark"));
            p.setFactoryId((String) pMap.get("factoryId"));
            p.setPackageForm((String) pMap.get("packageForm"));
            p.setFunctionDescription((String) pMap.get("functionDescription"));
            p.setCategory((String) pMap.get("category"));
            p.setSubCategory((String) pMap.get("subCategory"));
            productService.update(p);
        }

        Map map = new HashMap();
        map.put("result", "success");
        return map;
    }

}
