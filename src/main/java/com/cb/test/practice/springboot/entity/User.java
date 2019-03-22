package com.cb.test.practice.springboot.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
/**
 * @author 言曌
 * @date 2018/3/19 下午9:54
 */
@Entity
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 6147345506206285446L;
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Integer id; // 用户的唯一标识
    @NotEmpty(message = "昵称不能为空")
    @Size(min = 2, max = 12, message = "昵称长度必须为2-12个字符")
    @Column(nullable = false, length = 12)
    private String nickname;
    @NotEmpty(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度必须为4-20个字符")
    @Column(nullable = false, length = 20, unique = true)
    private String username; // 用户账号，用户登录时的唯一标识
    @NotEmpty(message = "密码不能为空")
    @Size(max = 100, message = "密码长度最多100个字符")
    @Column(length = 100)
    private String password; // 登录时密码
    @Column(length = 200)
    @Size(max = 200, message = "头像链接长度最多200个字符")
    private String avatar; // 头像图片地址
    private Integer followSize = 0;//关注数
    private Integer fanSize = 0; //粉丝数
    @Transient
    private Integer isFriend = 0;//关系，0表示没有关系，2表示互相关注
    public User() {
    }
}