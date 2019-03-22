package com.cb.test.practice.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
/**
 * 评论实体
 * @author 言曌
 * @date 2018/3/19 下午9:54
 */
@Entity
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -4502134548520740266L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id;
    @Column(name = "pid", nullable = false)
    private Long pid = 0L;//父评论，如果不设置，默认为0
    @NotEmpty(message = "评论内容不能为空")
    @Size(max = 500, message = "评论内容不能多于500个字符")
    @Column(nullable = false)
    private String content;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;//所属文章
    @OneToMany(fetch = FetchType.LAZY)//不设置级联，使用懒加载
    @JoinColumn(name = "pid", referencedColumnName = "id")
    @OrderBy("id ASC")
    private List<Comment> commentList;
    @JoinColumn(name = "reply_user_id")
    @OneToOne(fetch = FetchType.LAZY)//不设置级联，使用懒加载
    private User replyUser;
    public Comment() {
    }
    public Comment(User user,Post post,  String content) {
        this.post = post;
        this.content = content;
        this.user = user;
    }
    public Comment(User user, Post post, Long pid, String content) {
        this.user = user;
        this.post = post;
        this.pid = pid;
        this.content = content;
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", pid=" + pid +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}