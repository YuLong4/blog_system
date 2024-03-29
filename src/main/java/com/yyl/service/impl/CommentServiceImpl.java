package com.yyl.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyl.mapper.CommentMapper;
import com.yyl.mapper.StatisticMapper;
import com.yyl.model.domain.Comment;
import com.yyl.model.domain.Statistic;
import com.yyl.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    public StatisticMapper statisticMapper;

    // 根据文章id分页查询评论
    @Override
    public PageInfo<Comment> getComments(Integer aid, int page, int count) {
        PageHelper.startPage(page,count);
        List<Comment> commentList = commentMapper.selectCommentWithPage(aid);
        PageInfo<Comment> commentInfo = new PageInfo<>(commentList);
        return commentInfo;
    }

    //用户发表评论
    @Override
    public void pushComment(Comment comment){
        commentMapper.pushComment(comment);
        //更新文章评论数据量
        Statistic statistic = statisticMapper.selectStatisticWithArticleId(comment.getArticleId());
        statistic.setCommentsNum(statistic.getCommentsNum()+1);
        statisticMapper.updateArticleHitsWithId(statistic);
    }
}
