package com.ra.service;

import com.ra.model.User;
import com.ra.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public boolean register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
