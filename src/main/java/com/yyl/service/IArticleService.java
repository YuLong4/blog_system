package com.yyl.service;

import com.github.pagehelper.PageInfo;
import com.yyl.model.domain.Article;

import java.util.List;

/**
 * @Classname IArticleService
 * @Description TODO
 * @Date 2022-7-26 16:00
 * @Created by Yulong
 */
public interface IArticleService {
    // 分页查询文章列表
    public PageInfo<Article> selectArticleWithPage(Integer page, Integer count);

    // 统计热度排名前十的文章信息
    public List<Article> getHeatArticles();

    // 根据文章id查询单个文章详情
    public Article selectArticleWithId(Integer id);
}
