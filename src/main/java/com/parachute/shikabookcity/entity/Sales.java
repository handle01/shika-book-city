package com.parachute.shikabookcity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * (Sales)表实体类
 *
 * @author makejava
 * @since 2022-04-26 18:53:07
 */
@SuppressWarnings("serial")
public class Sales extends Model<Sales> {
    /**
     *书籍id
     */
    private Integer bookId;
    /**
     * 销售
     */
    private Integer sales;
    /**
     * 日期
     */
    private Date date;
    /**
     * 是否删除 1删除
     */
    private Integer delFlag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 名字
     */
    @TableField(exist = false)
    private String name;


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}

