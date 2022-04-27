package com.parachute.shikabookcity.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * (OrderCommodity)表实体类
 *
 * @author makejava
 * @since 2022-04-25 17:30:23
 */
@SuppressWarnings("serial")
public class OrderCommodity extends Model<OrderCommodity> {
    //订单id
    private String orderId;
    //书籍id
    private String commodityCode;
    //数量
    private Integer quantities;
    //是否删除 1为删除
    private String delFlag;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public Integer getQuantities() {
        return quantities;
    }

    public void setQuantities(Integer quantities) {
        this.quantities = quantities;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}

