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

import java.util.List;

/**
 * Created by Administrator on 16-4-27.
 */
@Controller
public class ProductDeletionController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "product/delete/{id}", method = RequestMethod.GET)
    public String delete(
            @PathVariable("id") String id
    ){
        productService.delete(id);
        return "";
    }

    @RequestMapping(value = "product/delete", method = RequestMethod.POST)
    public String delete(
            @RequestParam("ids") List<String> ids
    ){
        productService.delete(ids);
        return "";
    }

}
