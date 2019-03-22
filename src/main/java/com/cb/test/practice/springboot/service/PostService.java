package com.cb.test.practice.springboot.service;

import com.cb.test.practice.springboot.entity.Post;
import org.springframework.data.repository.Repository;

public interface PostService {
    Post getPostById(long postId);
}
