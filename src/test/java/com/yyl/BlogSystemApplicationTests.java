package com.yyl;

import com.yyl.mapper.ArticleMapper;
import com.yyl.mapper.CommentMapper;
import com.yyl.mapper.StatisticMapper;
import com.yyl.model.domain.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogSystemApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    StatisticMapper statisticMapper;

    @Test
    void testArticleMapper(){
        articleMapper.selectArticleWithId(1);

//        System.out.println(article);
//
//        articleMapper.deleteArticleWithId(1);
//
//        System.out.println(article);

//        System.out.println(articleMapper.publishArticle(article));
//
//        articleMapper.selectArticleWithPage();
//
//        articleMapper.countArticle();
//
//        articleMapper.updateArticleWithId(article);

        //no problem


    }


}
