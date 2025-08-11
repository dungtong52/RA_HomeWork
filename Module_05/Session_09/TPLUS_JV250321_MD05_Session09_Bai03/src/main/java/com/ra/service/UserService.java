package com.ra.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean login(String username, String password) {
        return username.equals("user") && "123456789".equals(password);
    }
}
