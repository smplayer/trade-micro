package com.as.erp.trade.micro.product.controller;

import com.as.common.util.FileUploadUtil;
import com.as.erp.trade.micro.product.entity.Product;
import com.as.erp.trade.micro.product.service.ProductService;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yrx on 2016/6/9.
 */
@Controller
public class ProductImageUploadController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "product/uploadImage", method = RequestMethod.GET)
    public String upload(
            @RequestParam("id") String id,
            ModelMap modelMap
    ) {
        modelMap.put("id", id);
        return "product/uploading-image";
    }

    @RequestMapping(value = "product/uploadImage", method = RequestMethod.POST)
    public String upload(
            @RequestParam("product-image") MultipartFile file,
            @RequestParam("id") String id,
            ModelMap modelMap,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String imagePath = FileUploadUtil.uploadFile(file, request);
         Product product = productService.getById(id);
        product.setImageURL(imagePath);
        productService.update(product);

        modelMap.put("id", id);
        modelMap.put("result", true);
        modelMap.put("imagePath", imagePath);

//        System.out.println(request.getSession().getServletContext().getRealPath( "/" ) );

        return "product/upload-image-success";
    }

}
