package com.parachute.shikabookcity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 * (Order)表实体类
 *
 * @author makejava
 * @date 2022/05/04
 * @since 2022-04-25 14:13:24
 */
@SuppressWarnings("serial")
@TableName("`order`")
public class Order extends Model<Order> {


    /**
     * 订单id
     */
    @TableId
    private String orderId;
    /**
     * 收货地址
     */
    private String address;
    /**
     *收货人
     */
    private String nickName;
    /**
     * 是否发货 0 未发货
     */
    private String isSend;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String updateName;
    /**
     * 收货人手机号码
     */
    private String phone;
    /**
     * 是否删除 1删除
     */
    @TableLogic
    private Integer delFlag;
    /**
     * 商品编号
     */
    @TableField(exist = false)
    private StringBuilder commodity;

    public StringBuilder getCommodity() {
        return commodity;
    }

    public void setCommodity(StringBuilder commodity) {
        this.commodity = commodity;
    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String userName) {
        this.nickName = userName;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
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

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }
    }

