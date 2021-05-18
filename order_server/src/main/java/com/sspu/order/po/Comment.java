package com.sspu.order.po;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
@Document(collection = "comment")
@CompoundIndex(def = "{'createdTime': 1}")
public class Comment implements Serializable {
    private static final long SerializableUID = 1L;

    /**
     * 用户名
     **/
    private String userName;
    /**
     * 评价内容
     **/
    @Field("content")
    private String content;
    /**
     * 店铺ID
     **/
    private int shop_id;
    /**
     * 评价时间
     **/
    private Date createdTime;
    /**
     * 评价星级
     **/
    private int star;
    /**
     * 回复数
     **/
    private Integer replynum;
    /**
     * 上级ID
     **/
    private String parentid;
    /**
     * 点赞数
     **/
    private Integer likenum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public static long getSerializableUID() {
        return SerializableUID;
    }

    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", shop_id=" + shop_id +
                ", createdTime=" + createdTime +
                ", star=" + star +
                ", replynum=" + replynum +
                ", parentid='" + parentid + '\'' +
                ", likenum=" + likenum +
                '}';
    }
}
