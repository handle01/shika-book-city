package com.parachute.shikabookcity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 活动
 * (Activity)表实体类
 *
 * @author makejava
 * @date 2022/05/04
 * @since 2022-04-23 15:58:31
 */
@SuppressWarnings("serial")
public class Activity extends Model<Activity> {

    /**
     * id
     */
    private Integer id;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 价格
     */
    private Double price;
    /**
     * 封面
     */
    private String cover;
    /**
     * 修改人
     */
    private String updateName;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否删除 1删除
     */
    @TableLogic
    private Integer delFlag;
    /**
     * 活动截止日期
     */
    private Date activityDeadline;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 0代表进行中
     */
    private String isDie;
    /**
     * 1已发布
     */
    private String isPublish;
    /**
     * 描述
     */
    private String description;
    /**
     * 活动时间范围
     */
    @TableField(exist = false)
    private List<String> publishTime;
    /**
     * 活动所包含的书籍
     */
    @TableField(exist = false)
    private List<Integer> books;
    /**
     * 商品编码
     */
    private String commodityCode;

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public List<String> getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(List<String> publishTime) {
        this.publishTime = publishTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public List<Integer> getBooks() {
        return books;
    }

    public void setBooks(List<Integer> books) {
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getActivityDeadline() {
        return activityDeadline;
    }

    public void setActivityDeadline(Date activityDeadline) {
        this.activityDeadline = activityDeadline;
    }

    public String getIsDie() {
        return isDie;
    }

    public void setIsDie(String isDie) {
        this.isDie = isDie;
    }

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

