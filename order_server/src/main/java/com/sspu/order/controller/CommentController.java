package com.sspu.order.controller;

import com.sspu.order.po.Comment;
import com.sspu.order.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 查看店铺评论
     */
    public List<Comment> findAllCommentsByShopId(@Param("shopId") int shop_id) {
        return commentService.findAllCommentByShopId(shop_id);
    }

    /**
     * 对店铺进行评论
     */
    public void initCommentByUserName(@Param("username") String username,
                                      @Param("shopId") int shop_id,
                                      @Param("content") String content
    ) {
        Comment comment = new Comment();
        comment.setCreatedTime(new Date());
        comment.setLikenum(0);
        comment.setShop_id(shop_id);
        comment.setUserName(username);
        comment.setContent(content);
        comment.setParentid(null);
        comment.setReplynum(0);
        commentService.save(comment);
    }
}
