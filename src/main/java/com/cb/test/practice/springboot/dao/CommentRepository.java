package com.cb.test.practice.springboot.dao;

import com.cb.test.practice.springboot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
