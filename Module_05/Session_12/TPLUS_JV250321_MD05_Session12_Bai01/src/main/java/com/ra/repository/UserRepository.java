package com.ra.repository;

import com.ra.model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    void update(User user);

    List<User> findAllFriend(Long id);

}
