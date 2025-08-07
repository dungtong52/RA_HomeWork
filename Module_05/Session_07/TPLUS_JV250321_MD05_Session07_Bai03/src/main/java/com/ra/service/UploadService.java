package com.ra.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {
    private final String uploadDir = "./uploads/";

    public String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            File dest = new File(uploadDir + fileName);
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(dest);
            return dest.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
