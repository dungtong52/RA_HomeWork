package com.ra.service;

import com.ra.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPostByUser(Long userId);

    void save(Post post);

    Post findById(Long id);

    void delete(Long id);
}
