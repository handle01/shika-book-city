package com.parachute.shikabookcity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Book)表实体类
 *
 * @author makejava
 * @since 2022-04-19 14:23:02
 */
@SuppressWarnings("serial")
public class Book extends Model<Book> {
    //id
    private Integer id;
    //书籍姓名
    private String name;
    //ISBN
    private String isbn;
    //商品编码
    private String commodityCode;
    //出版时间
    @TableField("publication_time")
    private String publicationTime;
    //品牌
    private String brand;
    //包装类型
    private String packaging;
    //开本类型
    private String format;
    //用纸类型
    private String thePaper;
    //封面
    private String cover;
    //价格
    private Double price;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //否删除 1 删除
    @TableLogic
    private Integer delFlag;
    //修改人
    private String updateName;
    //作者
    private String author;
    //出版社
    private String press;
    //是否上架 1上架
    private Integer isAdded;
    //描述
    private String description;
    @TableField(exist = false)
    private List<String> type;

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getThePaper() {
        return thePaper;
    }

    public void setThePaper(String thePaper) {
        this.thePaper = thePaper;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Integer getIsAdded() {
        return isAdded;
    }

    public void setIsAdded(Integer isAdded) {
        this.isAdded = isAdded;
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

