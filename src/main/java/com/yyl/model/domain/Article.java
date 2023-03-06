package com.yyl.model.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 博客文章实体类
 * 1、使用定制的RedisConfig配置类，实现缓存时的JSON序列化机制
 *
 * 2023/03/06
 * 用lombok简化get、set代码
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Article {
    private Integer id;          // 文章id
    private String title;       // 文章标题
    private String content;     // 文章内容
    private Date created;       // 文章创建时间
    private Date modified;      // 文章创建时间
    private String categories;  // 文章分类
    private String tags;        // 文章标签
    private Boolean allowComment;// 是否允许评论，默认为true
    private String thumbnail;   // 文章缩略图
    private Integer hits;       // 点击量
    private Integer commentsNum;// 评论总量
}
