package com.example.aiproject2.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aiproject2.PdfExtractService;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.alibaba.fastjson.JSON;
@Controller
@RequestMapping("/file")
public class uploadcontroller {
    @Autowired
    private PdfExtractService pdfExtractService;
    @PostMapping("/uploadFile")
    @ResponseBody
    public JSONObject uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletReques)
    {

        System.out.println("UPLOAD START");
        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
            String fileName = myFileName;
            String filePath="C:\\Users\\74583\\TEST";
            String filePath2="C:\\\\Users\\\\74583\\\\TEST";
            String JsonReturn="{'FileName':'"+fileName+"'}";
            File fileDir=new File(filePath);
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }
            String path=filePath2+"\\\\"+fileName;
            File localFile = new File(filePath,fileName);
            try {
                System.out.println("Original File Name:"+myFileName+"LocalFIle"+localFile.toString());
                file.transferTo(localFile);
                try {
                    System.out.println(path);
                    pdfExtractService.ExtractPdf(path);
                }
                catch (Exception e)
                {
                    System.out.println(e+"PDFEXTRACT");
                }
                return JSONObject.parseObject(JsonReturn);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            System.out.println("文件为空");
        }
        return JSONObject.parseObject("{ERROR}");

    }
}
