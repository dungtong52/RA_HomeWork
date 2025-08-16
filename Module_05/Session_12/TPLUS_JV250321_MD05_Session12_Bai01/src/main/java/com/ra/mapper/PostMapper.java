package com.ra.mapper;

import com.ra.dto.PostDto;
import com.ra.model.Post;
import com.ra.model.User;

import java.time.LocalDate;

public class PostMapper {
    public static Post toModel(PostDto dto, String imageUrl, User currentUser){
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setImage(imageUrl);
        post.setCreatedDate(LocalDate.now());
        post.setUser(currentUser);
        return post;
    }
}
