package com.sspu.order.service;

import com.sspu.order.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 给予评论
     */
    public Comment save(Comment comment) {
        return mongoTemplate.save(comment);
    }

    /**
     * 查看店铺评论
     */
    public List<Comment> findAllCommentByShopId(int shop_id) {
        Query query = new Query(Criteria.where("shop_id").is(shop_id));
        return mongoTemplate.find(query, Comment.class, "comment");
    }
}
