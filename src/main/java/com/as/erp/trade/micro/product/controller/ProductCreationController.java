package com.as.erp.trade.micro.product.controller;

import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 16-4-26.
 */
@Controller
public class ProductCreationController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "product/create", method = RequestMethod.GET)
    public String create(){
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
    ){

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
        productService.save(p);

        return "redirect:/product/create";
    }


}
