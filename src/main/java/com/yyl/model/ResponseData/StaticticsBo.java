package com.yyl.model.ResponseData;


/**
 * 全站服务统计类
 */
public class StaticticsBo {
    private Integer articles;           //文章
    private Integer comments;            //评论

    public Integer getArticles() {
        return articles;
    }

    public void setArticles(Integer articles) {
        this.articles = articles;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "StaticticsBo{" +
                "articles=" + articles +
                ", comments=" + comments +
                '}';
    }
}
