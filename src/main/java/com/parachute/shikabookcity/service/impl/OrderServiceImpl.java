package com.parachute.shikabookcity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.shikabookcity.dao.OrderDao;
import com.parachute.shikabookcity.entity.Expressage;
import com.parachute.shikabookcity.entity.Order;
import com.parachute.shikabookcity.entity.OrderCommodity;
import com.parachute.shikabookcity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2022-04-25 14:13:25
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

    @Autowired
    private OrderDao orderDao;
    /**
     * 获取订单信息
     *
     * @param userName 用户名
     * @return {@link List}<{@link Order}>
     */
    @Override
    public List<Order> getOrder(String userName) {
        List<Order> orders = orderDao.getOrder(userName);
        orders.forEach(order -> {
            //订单商品信息
            StringBuffer commodity = new StringBuffer("");
            //获取商品编号
            List<OrderCommodity> codes = getCommodityCode(order.getOrderId());
            codes.forEach(code->{
                String bookName =  getBookName(code.getCommodityCode());
                String activityName =  getActivityName(code.getCommodityCode());
                if (bookName != null){
                    commodity.append(bookName).append("x").append(code.getQuantities()).append("; ");
                }
                if (activityName != null){
                    commodity.append(activityName).append("x").append(code.getQuantities());
                }
            });
            order.setCommodity(commodity);
        });
        return orders;
    }

    /**
     * 获得商品编码
     *
     * @param orderId 订单id
     * @return {@link List}<{@link OrderCommodity}>
     */
    @Override
    public List<OrderCommodity> getCommodityCode(String orderId) {
        return orderDao.getOrderCommodityCode(orderId);
    }

    /**
     * 获取书籍名称
     *
     * @param commodityCode 商品编码
     * @return {@link String}
     */
    @Override
    public String getBookName(String commodityCode) {

        return orderDao.getBookName(commodityCode);
    }

    /**
     * 获取活动名称
     *
     * @param commodityCode 商品编码
     * @return {@link String}
     */
    @Override
    public String getActivityName(String commodityCode) {

        return orderDao.getActivityName(commodityCode);
    }

    /**
     * 发货
     *
     * @param orderId 订单id
     */
    @Override
    public void send(String orderId) {
        orderDao.send(orderId);

    }

    /**
     * 设置已发货数据到数据库
     *
     * @param map 地图
     */
    @Override
    public void insertSend(Map map) {
        String orderId = (String) map.get("orderId");
        send(orderId);
        String nickName = (String)map.get("nickName");
        String phone = (String)map.get("phone");
        String commodity = (String) map.get("commodity");
        String position = "打包中";
        orderDao.setSend(orderId,position,nickName,phone,commodity);
    }

    /**
     * 得到订单快递业务
     *
     * @param userName 用户名
     * @return {@link List}<{@link Expressage}>
     */
    @Override
    public List<Expressage> getOrderExpressage(String userName) {
        return orderDao.getOrderExpressage(userName);

    }

    /**
     * 获取已签收数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Expressage}>
     */
    @Override
    public List<Expressage> getSigned(String userName) {
        return orderDao.getSigned(userName);
    }

}

