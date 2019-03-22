package com.cb.test.practice.springboot.entity;

import lombok.Data;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
/**
 * @author 言曌
 * @date 2018/3/19 下午9:54
 */
@Entity
@Data
public class Post implements Serializable {
    private static final long serialVersionUID = -5086173193716866676L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    @Column(name = "id", nullable = false)
    private Long id;
    @NotEmpty(message = "标题不能为空")
    @Size(max = 200, message = "标题不能超过200个字符")
    @Column(nullable = false, length = 200)
    private String title;
    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(max = 100000, message = "内容不能超过100000个字符")
    @Column(nullable = false)
    private String content;//文章全文内容
    @Column(name = "comment_size")
    private Integer commentSize = 0;  // 评论数
    //当文章删除后，与之关联的评论也会被删除
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    @Where(clause = "pid=0")
    private List<Comment> commentList;

    public Post() {
    }

}