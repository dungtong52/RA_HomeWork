package com.ra.service.imp;

import com.ra.model.Post;
import com.ra.repository.PostRepository;
import com.ra.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPostByUser(Long userId) {
        return postRepository.findAllPost(userId);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(id);
    }
}
