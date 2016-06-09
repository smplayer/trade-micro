package com.as.common.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by yrx on 2016/5/29.
 */
@Component
public class FileUploadUtil {

    public static final String FILE_PATH = "resources/upload/";

    //文件上传
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath( "/" ) + FILE_PATH;

//        String fileName = file.getOriginalFilename();
//        File tempFile = new File(path, new Date().getTime() + String.valueOf(fileName));

        String newFileName = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File newFile = new File(path, newFileName);
//        File newFile = new File(path, new Date().getTime() + String.valueOf(file.getOriginalFilename()));

        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdir();
        }
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        file.transferTo(newFile);
//        return "/download?fileName=" + newFile.getName();
        return newFileName;
    }

    public static File getFile(String fileName, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath( "/" ) + FILE_PATH;
        return new File(path, fileName);
    }

}
