package com.parachute.shikabookcity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * 书评论
 * (BookComment)表实体类
 *
 * @author makejava
 * @date 2022/05/04
 * @since 2022-04-26 15:04:20
 */
@SuppressWarnings("serial")
public class BookComment extends Model<BookComment> {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 评论
     */
    private String comment;
    /**
     * 头像
     */
    private String profile;
    /**
     * 商品编码
     */
    private String commodityCode;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 评论时间
     */
    @TableField("b.`name`")
    private Date commentTime;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

}

