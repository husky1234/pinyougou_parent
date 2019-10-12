package com.pinyougou.manager.controller;

import com.pinyougou.pojo.Result;
import com.pinyougou.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class UploadController {
    @Value("http://192.168.25.133/")
    private String FILE_SERVER_URL;
    @RequestMapping("/upload")
    public Result upload(MultipartFile file){
        try {
            System.out.println("aaaa");
            //1.1）得到原来的文件名
            String filename = file.getOriginalFilename();
            //1.2)得到文件的后缀名
            String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
            //1.3)开始进行文件上传
            //1.3.1)构造FastDFSClient对象
            FastDFSClient client = new FastDFSClient("classpath:config/fdfs_client.conf");
            //1.3.2)进行文件上传
            String path = client.uploadFile(file.getBytes(), suffixName);
            //1.3.3)根据路径拼凑文件的url地址
            String url = FILE_SERVER_URL + path;
            //1.3.4)返回
            return new Result(true,url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"上传文件失败！");
        }
    }
}
