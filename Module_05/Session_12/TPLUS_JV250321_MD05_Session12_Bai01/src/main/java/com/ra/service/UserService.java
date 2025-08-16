package com.ra.service;

import com.ra.model.User;

import java.util.List;

public interface UserService {
    void register(User user);

    User login(String email, String password);

    List<User> findAll();

    void update(User user);

    User findById(Long id);

    User findByEmail(String email);

    void addFriend(User currentUser, User friend);
}
