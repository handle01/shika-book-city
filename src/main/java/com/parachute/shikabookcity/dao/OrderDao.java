package com.parachute.shikabookcity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.shikabookcity.entity.Expressage;
import com.parachute.shikabookcity.entity.Order;
import com.parachute.shikabookcity.entity.OrderCommodity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * (Order)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-25 14:13:23
 */
@Mapper
public interface OrderDao extends BaseMapper<Order> {

    /**
     * 获取订单数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Order}>
     */
    @Select("SELECT o.order_id,o.address,o.nick_name,o.phone " +
            "from `user` u, user_order uo,`order` o " +
            "WHERE u.id = uo.user_id and o.del_flag = 0 and o.is_send = 0 and o.order_id = uo.order_id and u.user_name = #{userName}")
    List<Order> getOrder(String userName);

    /**
     * 得到商品编码
     *
     * @param orderId 订单id
     * @return {@link List}<{@link String}>
     */
    @Select("SELECT oc.commodity_code,oc.quantities " +
            "FROM order_commodity oc,`order` o " +
            "WHERE o.order_id = oc.order_id and oc.del_flag = 0 and o.order_id = #{orderId}")
    List<OrderCommodity> getOrderCommodityCode(String orderId);


    /**
     * 获取书名
     *
     * @param commodityCode 商品编码
     * @return {@link List}<{@link String}>
     */
    @Select("SELECT b.`name` from book b WHERE b.commodity_code = #{commodityCode}")
    String getBookName(String commodityCode);

    /**
     * 获取活动名字
     *
     * @param commodityCode 商品编码
     * @return {@link List}<{@link String}>
     */
    @Select("SELECT a.activity_name from activity a WHERE a.commodity_code = #{commodityCode}")
    String getActivityName(String commodityCode);

    /**
     * 发送
     *
     * @param orderId 订单id
     */
    @Update("update `order` set is_send = 1 where order_id = #{orderId}")
    void send(String orderId);

    /**
     * 将发货的数据存入对应的数据库表里
     *
     * @param orderId   订单id
     * @param position  位置
     * @param nickName  尼克名字
     * @param phone     电话
     * @param commodity 商品
     */
    @Insert("INSERT INTO expressage (order_id,position,nick_name,phone,commodity) VALUES(#{orderId},#{position},#{nickName},#{phone},#{commodity})")
    void setSend(@Param("orderId") String orderId,@Param("position") String position,@Param("nickName") String nickName,@Param("phone") String phone,@Param("commodity") String commodity);


    /**
     * 得到订单快递业务
     *
     * @param userName 用户名
     * @return {@link List}<{@link Expressage}>
     */
    @Select("select e.order_id,e.nick_name,e.phone,e.position,e.commodity " +
            "from user u, expressage e,user_order uo " +
            "WHERE u.id = uo.user_id and uo.order_id = e.order_id and e.is_signed = 0 and u.user_name = #{userName}"
           )
    List<Expressage> getOrderExpressage(String userName);

    /**
     * 获取与签收数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Expressage}>
     */
    @Select("select e.order_id,e.nick_name,e.phone,e.position,e.commodity " +
            "from user u, expressage e,user_order uo " +
            "WHERE u.id = uo.user_id and uo.order_id = e.order_id and e.is_signed = 1 and u.user_name = #{userName}"
    )
    List<Expressage> getSigned(String userName);

}

