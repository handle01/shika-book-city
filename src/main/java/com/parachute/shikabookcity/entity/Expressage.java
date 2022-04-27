package com.parachute.shikabookcity.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * (Expressage)表实体类
 *
 * @author makejava
 * @since 2022-04-26 10:24:04
 */
@SuppressWarnings("serial")
public class Expressage extends Model<Expressage> {
    //订单号
    private String orderId;
    //当前位置
    private String position;
    //是否删除 1为删除
    private String delFlag;
    //收货人
    private String nickName;
    //电话号码
    private String phone;
    //1为已签收
    private String isSigned;
    //商品信息
    private String commodity;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsSigned() {
        return isSigned;
    }

    public void setIsSigned(String isSigned) {
        this.isSigned = isSigned;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

}

