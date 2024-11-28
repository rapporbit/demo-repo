package com.rapporbit.controller;

import com.rapporbit.pojo.Result;
import com.rapporbit.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    /**
     * 本地存储
     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("name: {}, age: {}, file: {}", name, age, file);
//        String originalFilename = file.getOriginalFilename();
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFilename = UUID.randomUUID().toString() + extension;
//        file.transferTo(new File("/Users/zjl/Documents/upload/" + newFilename));
//        return Result.success();
//
//    }

    @Autowired
    AliyunOSSOperator aliyunOSSOperator;
    /**
     * 阿里云OSS存储
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("file: {}", file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String url = aliyunOSSOperator.upload(file.getBytes(), originalFilename);
        return Result.success(url);
    }
}
