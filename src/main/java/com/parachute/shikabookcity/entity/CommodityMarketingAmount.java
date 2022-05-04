package com.parachute.shikabookcity.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * (MarketingAmount)表实体类
 *
 * @author makejava
 * @since 2022-04-26 22:14:27
 */
@SuppressWarnings("serial")
public class CommodityMarketingAmount extends Model<CommodityMarketingAmount> {
    /**
     * 日期
     */
    private Date date;
    /**
     * 销售数量
     */
    private String marketingAmount;
    /**
     * 商品编码
     */
    private String commodityCode;
    /**
     * 商品名
     */
    private String name;
    /**
     * 是否删除
     */
    private String delFlag;


    public Date getData() {
        return date;
    }

    public void setData(Date data) {
        this.date = data;
    }

    public String getMarketingAmount() {
        return marketingAmount;
    }

    public void setMarketingAmount(String marketingAmount) {
        this.marketingAmount = marketingAmount;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}

