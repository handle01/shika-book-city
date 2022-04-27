package com.parachute.shikabookcity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.shikabookcity.entity.Sales;

import java.util.HashMap;
import java.util.List;

/**
 * (Sales)表服务接口
 *
 * @author machi
 * @since 2022-04-15 23:57:06
 */
public interface SalesService extends IService<Sales> {

    /**
     * 获得当天的销量
     *
     * @param id id
     * @return {@link List}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    List<HashMap<String, Object>> getDaily(Integer id);

    /**
     * 获得当月的销量
     *
     * @param id id
     * @return {@link List}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    List<HashMap<String, Object>> getMonthly(Integer id);

    /**
     * 获得一年的销量
     *
     * @param id id
     * @return {@link List}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    List<HashMap<String, Object>> getYear(Integer id);

    /**
     * 获得商品销售额
     *
     * @param id id
     * @return {@link List}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    List<HashMap<String, Object>> getCommodityMarketingAmount(Integer id);


    /**
     * 获得年度销售额
     *
     * @param id id
     * @return {@link List}<{@link List}<{@link String}>>
     */
    List<List<String>> getYearMarketingAmount(Integer id);

    /**
     * 得到月销售额
     *
     * @param id   id
     * @param date 日期
     * @return {@link String}
     */
    String getMonthMarketingAmount(Integer id,String date);
}

