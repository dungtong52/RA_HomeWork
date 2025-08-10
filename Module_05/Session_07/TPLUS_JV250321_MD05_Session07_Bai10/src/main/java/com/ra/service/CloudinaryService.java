package com.ra.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        File temp = File.createTempFile("upload", multipartFile.getOriginalFilename());
        multipartFile.transferTo(temp);

        Map uploadResult = cloudinary.uploader().upload(temp, ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString();
    }
}
