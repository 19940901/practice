package com.cb.test.practice.springboot.controllers;

import com.cb.test.practice.springboot.entity.Comment;
import com.cb.test.practice.springboot.entity.Post;
import com.cb.test.practice.springboot.service.CommentService;
import com.cb.test.practice.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


public class SeparatePage {

    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    /**
     * ajax 获取评论页面数据
     *
     * @param postId
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listComments(
            @RequestParam(value = "postId") Long postId,
            @RequestParam(value = "async", required = false, defaultValue = "new") Boolean async,
            @RequestParam(value = "order", required = false, defaultValue = "new") String order,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
            Model model) {
        Page<Comment> commentPage = null;
        Post post = postService.getPostById(postId);
        String commentOrder = "new";
        try {
            if (order.equals("hot")) { // 最热查询
                Sort sort = new Sort(Sort.Direction.DESC, "zanSize", "id");  //根据点赞数量排序
                Pageable pageable = new PageRequest(pageIndex - 1, pageSize, sort);
                commentPage = commentService.listCommentByPost(post, pageable);
                commentOrder = "hot";
            } else if (order.equals("new")) { // 最新查询
                SpringDataWebProperties.Sort sort = new SpringDataWebProperties.Sort(SpringDataWebProperties.Sort.Direction.DESC, "id");
                SpringDataWebProperties.Pageable pageable = new PageRequest(pageIndex - 1, pageSize, sort);
                commentPage = commentService.listCommentByPost(post, pageable);
            }
        } catch (Exception e) {
            Pageable pageable = new PageRequest(0, 10);
            commentPage = commentService.listCommentByPost(post, pageable);
        }
        Integer commentSize = post.getCommentSize();
        model.addAttribute("page", commentPage);
        model.addAttribute("commentSize", commentSize);
        model.addAttribute("commentOrder", commentOrder);
        model.addAttribute("post", post);
        return new ModelAndView(async == true ? "home/post_detail :: #comment" : "home/post_detail");
    }
}
