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
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        File temp = File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(temp);

        Map uploadResult = cloudinary.uploader().upload(temp, ObjectUtils.emptyMap());

        temp.delete();
        return uploadResult.get("secure_url").toString();
    }
}
