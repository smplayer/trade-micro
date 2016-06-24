package com.as.erp.trade.micro.product.controller;

import com.as.erp.trade.micro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-4-27.
 */
@Controller
public class ProductDeletionController {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "product/delete")
    public Object delete(
            @RequestBody List<String> ids
    ) {
        productService.delete(ids);

        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;

    }

}
