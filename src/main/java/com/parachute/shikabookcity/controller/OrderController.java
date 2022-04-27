package com.parachute.shikabookcity.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parachute.shikabookcity.entity.Expressage;
import com.parachute.shikabookcity.entity.Order;
import com.parachute.shikabookcity.service.OrderService;
import com.parachute.shikabookcity.util.Page;
import com.parachute.shikabookcity.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2022-04-25 14:13:22
 */
@RestController
@RequestMapping("order")
public class OrderController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private OrderService orderService;

    /**
     * 获取订单信息
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping("getOrder")
    public Result getOrder( Page page){
        PageHelper.startPage(page.getCurrent(),page.getSize());
        List<Order> orders = orderService.getOrder(page.getUserName());
        PageInfo<Order> info = new PageInfo<>(orders);
        return Result.of(true,"",info);
    }


    /**
     * 确认发货
     *
     * @param map 数据
     * @return {@link Result}
     */
    @RequestMapping("send")
    public Result send(@RequestBody Map map){
        String orderId = (String) map.get("orderId");
        try {
            orderService.send(orderId);
            orderService.setSend(map);
            return Result.of(true,"确认发货成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.of(false,"服务器异常");
        }

    }

    /**
     * 获取订单快递业务
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping("getOrderExpressage")
    public Result getOrderExpressage(Page page){
        try {
            PageHelper.startPage(page.getCurrent(), page.getSize());
            List<Expressage> expressages =  orderService.getOrderExpressage(page.getUserName());
            PageInfo<Expressage> pageInfo = new PageInfo<>(expressages);
            return Result.of(true,"",pageInfo);
        }catch (Exception e){
            return Result.of(false,"服务器异常");
        }
    }

    /**
     * 得到签收数据
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping("getSigned")
    public Result getSigned(Page page){
        try {
            PageHelper.startPage(page.getCurrent(), page.getSize());
            List<Expressage> signed =  orderService.getSigned(page.getUserName());
            PageInfo<Expressage> pageInfo = new PageInfo<>(signed);
            return Result.of(true,"",pageInfo);
        }catch (Exception e){
            return Result.of(false,"服务器异常");
        }

    }


}

