package com.cb.test.practice.springboot.service;

import com.cb.test.practice.springboot.dao.CommentRepository;
import com.cb.test.practice.springboot.dao.PostRepository;
import com.cb.test.practice.springboot.entity.Comment;
import com.cb.test.practice.springboot.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    @Transactional
    public void removeComment(Long id) {
        commentRepository.deleteById(id);
    }
    @Override
    public Integer countCommentSizeByPost(Post post) {
        return commentRepository.countByPost(post);
    }
    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).get();
    }
    @Override
    public void createComment(Long postId, String commentContent) {
        Post post = postRepository.findById(postId).get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment(user, post, commentContent);
        commentRepository.save(comment);
    }
    @Override
    public void replyComment(Long postId, Long commentId, Long replyId, String commentContent) {
        Post post = postRepository.findById(postId).get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment(user, post, commentId, commentContent);
        //评论回复，需要加上@用户
        if (replyId != null) {
            Comment replyComment = commentRepository.findById(replyId).get();
            comment.setReplyUser(replyComment.getUser());
        }
        //添加评论
        commentRepository.save(comment);
    }
}
