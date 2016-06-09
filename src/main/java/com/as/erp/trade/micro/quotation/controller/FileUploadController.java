package com.as.erp.trade.micro.quotation.controller;

import com.as.common.util.FileUploadUtil;
import com.as.erp.trade.micro.quotation.entity.QuotationProductItemDraft;
import com.as.erp.trade.micro.quotation.service.QuotationProductItemDraftService;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.OutputStream;

/**
 * Created by yrx on 2016/5/29.
 */
@Controller
public class FileUploadController {
    Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private QuotationProductItemDraftService quotationProductItemDraftService;

    @RequestMapping(value = "quotation/uploadImage", method = RequestMethod.GET)
    public String upload(
            @RequestParam("id") String id,
            ModelMap modelMap
    ) {
        modelMap.put("id", id);
        return "quotation/uploading-image";
    }

    @RequestMapping(value = "quotation/uploadImage", method = RequestMethod.POST)
    public String upload(
            @RequestParam("product-image") MultipartFile file,
            @RequestParam("id") String id,
            ModelMap modelMap,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String imagePath = FileUploadUtil.uploadFile(file, request);
        QuotationProductItemDraft draft = quotationProductItemDraftService.getById(id);
        draft.setImageURL(imagePath);
        quotationProductItemDraftService.update(draft);

//        logger.info("filePath:" + imagePath);
//        response.setContentType("text/html;charset=utf8");
//        response.getWriter().write("<img src='"+filePath+"'/>");
        modelMap.put("id", id);
        modelMap.put("result", true);
        modelMap.put("imagePath", imagePath);

//        System.out.println(request.getSession().getServletContext().getRealPath( "/" ) );

        return "quotation/upload-image-success";
    }

    @RequestMapping("download")
    public void download(String fileName,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("image/jpeg; charset=utf-8");
            os.write(FileUtils.readFileToByteArray(FileUploadUtil.getFile(fileName, request)));
            os.flush();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

}
