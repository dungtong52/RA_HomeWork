package com.ra.model;

import org.springframework.web.multipart.MultipartFile;

public class UserProfile {
    private String username;
    private MultipartFile avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}

