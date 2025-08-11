package com.ra.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserProfile {
    private MultipartFile file;
    private String name;
}
