package com.cb.test.practice.springboot.service;

import com.cb.test.practice.springboot.entity.Comment;
import com.cb.test.practice.springboot.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Page<Comment> listCommentByPost(Post post, Pageable pageable);

    void removeComment(Long id);

    Integer countCommentSizeByPost(Post post);

    Comment getCommentById(Long id);

    void createComment(Long postId, String commentContent);

    void replyComment(Long postId, Long commentId, Long replyId, String commentContent);
}
