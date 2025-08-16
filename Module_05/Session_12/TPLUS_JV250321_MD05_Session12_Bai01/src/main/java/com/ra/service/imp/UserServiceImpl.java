package com.ra.service.imp;

import com.ra.model.User;
import com.ra.repository.UserRepository;
import com.ra.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void addFriend(User currentUser, User friend) {
        if (!currentUser.getFriends().contains(friend)) {
            currentUser.getFriends().add(friend);
            friend.getFriends().add(currentUser);
            userRepository.update(currentUser);
            userRepository.update(friend);
        }
    }

    @Override
    public List<User> findAllFriend(Long id) {
        return userRepository.findAllFriend(id);
    }
}
