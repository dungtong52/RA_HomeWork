package com.ra.repository;

import com.ra.model.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAllPost(Long userId);

    void save(Post post);

    Post findById(Long id);

    void delete(Long id);
}
