package com.parachute.shikabookcity.controller;


import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.parachute.shikabookcity.constant.ResultConstant;
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
public class OrderController {
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
        PageMethod.startPage(page.getCurrent(),page.getSize());
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
    public Result send(@RequestBody Map<String,Object> map){
        try {
            orderService.insertSend(map);
            return Result.of(true, ResultConstant.SHIPMENTS_SUCCEED);
        }catch (Exception e){
            e.printStackTrace();
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
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
            PageMethod.startPage(page.getCurrent(), page.getSize());
            List<Expressage> expressages =  orderService.getOrderExpressage(page.getUserName());
            PageInfo<Expressage> pageInfo = new PageInfo<>(expressages);
            return Result.of(true,"",pageInfo);
        }catch (Exception e){
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
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
            PageMethod.startPage(page.getCurrent(), page.getSize());
            List<Expressage> signed =  orderService.getSigned(page.getUserName());
            PageInfo<Expressage> pageInfo = new PageInfo<>(signed);
            return Result.of(true,"",pageInfo);
        }catch (Exception e){
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }

    }


}

