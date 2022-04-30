package com.parachute.shikabookcity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.shikabookcity.entity.Expressage;
import com.parachute.shikabookcity.entity.Order;
import com.parachute.shikabookcity.entity.OrderCommodity;

import java.util.List;
import java.util.Map;

/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2022-04-25 14:13:24
 */
public interface OrderService extends IService<Order> {

    /**
     * 获取订单信息
     *
     * @param userName 用户名
     * @return {@link List}<{@link Order}>
     */
    List<Order> getOrder(String userName);

    /**
     * 获得商品编码
     *
     * @param orderId 订单id
     * @return {@link List}<{@link }>
     */
    List<OrderCommodity> getCommodityCode(String orderId);

    /**
     * 获取书名字
     *
     * @param commodityCode 商品编码
     * @return {@link String}
     */
    String getBookName(String commodityCode);

    /**
     * 获取活动名字
     *
     * @param commodityCode 商品编码
     * @return {@link String}
     */
    String getActivityName(String commodityCode);

    /**
     * 发货
     *
     * @param orderId 订单id
     */
    void send(String orderId);

    /**
     * 设置发送数据到数据库
     *
     * @param map 地图
     */
    void insertSend(Map map);

    /**
     * 得到订单快递业务
     *
     * @param userName 用户名
     * @return {@link List}<{@link Expressage}>
     */
    List<Expressage> getOrderExpressage(String userName);

    /**
     * 获取已签收数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Expressage}>
     */
    List<Expressage> getSigned(String userName);
}

