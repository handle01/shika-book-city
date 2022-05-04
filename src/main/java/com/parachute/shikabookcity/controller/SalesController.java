package com.parachute.shikabookcity.controller;


import com.parachute.shikabookcity.constant.ResultConstant;
import com.parachute.shikabookcity.service.SalesService;
import com.parachute.shikabookcity.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * (Sales)表控制层
 *
 * @author makejava
 * @since 2022-04-15 23:57:05
 */
@RestController
@RequestMapping("sales")
public class SalesController{
    /**
     * 服务对象
     */
    @Resource
    private SalesService salesService;

    /**
     * 获得当天销量
     *
     * @param id id
     * @return {@link Result}
     */
    @RequestMapping("getDaily")
    public Result getDaily(Integer id){
        try {
            List<HashMap<String, Object>> sales = salesService.getDaily(id);
            return Result.of(true,"",sales);
        }catch (Exception e){
            e.printStackTrace();
            return Result.of(false, ResultConstant.SERVER_EXCEPTION);
        }

    }

    /**
     * 获得当月销量
     *
     * @param id id
     * @return {@link Result}
     */
    @RequestMapping("getMonthly")
    public Result getMonthly(Integer id){
        try {
            List<HashMap<String, Object>> sales = salesService.getMonthly(id);
            return Result.of(true,"",sales);
        }catch (Exception e){
            e.printStackTrace();
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }
    }

    /**
     * 获得一年的销量
     *
     * @param id id
     * @return {@link Result}
     */
    @RequestMapping("getYear")
    public Result getYear(Integer id){
        try {
            List<HashMap<String, Object>> sales = salesService.getYear(id);
            return Result.of(true,"",sales);
        }catch (Exception e){
            e.printStackTrace();
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }
    }


    /**
     * 获得商品销售额
     *
     * @param id id
     * @return {@link Result}
     */
    @RequestMapping("getCommodityMarketingAmount")
    public Result getCommodityMarketingAmount(Integer id){
        try {
            List<HashMap<String, Object>> marketingAmounts = salesService.getCommodityMarketingAmount(id);
            return Result.of(true,"",marketingAmounts);
        }catch (Exception e){
            e.printStackTrace();
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }
    }

    /**
     * 获得年度销售额
     *
     * @param id id
     * @return {@link Result}
     */
    @RequestMapping("getYearMarketingAmount")
    public Result getYearMarketingAmount(Integer id){
        try {
            List<List<String>> list = salesService.getYearMarketingAmount(id);
            return Result.of(true,"",list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }

    }
}

