package com.parachute.shikabookcity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.shikabookcity.entity.CommodityMarketingAmount;
import com.parachute.shikabookcity.entity.Sales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Sales)表数据库访问层
 *
 * @author machi
 * @since 2022-04-15 23:57:06
 */
@Mapper
public interface SalesDao extends BaseMapper<Sales> {

    /**
     * 获得当天的销量
     *
     * @param id   id
     * @param date 日期
     * @return {@link List}<{@link Sales}>
     */
    @Select("SELECT b.`name`,s.sales " +
            "FROM sales s,user u,book b,user_book ub " +
            "WHERE u.id = ub.user_id and ub.book_id = b.id and b.id = s.book_id and u.id = #{id} and s.date = #{data}")
    List<Sales> getDaily(@Param("id") Integer id,@Param("data") String date);

    /**
     * 获得当月的销量
     *
     * @param id   id
     * @param date 日期
     * @return {@link List}<{@link Sales}>
     */
    @Select("SELECT b.`name`,s.sales " +
            "FROM sales s,user u,book b,user_book ub " +
            "WHERE u.id = ub.user_id and ub.book_id = b.id and b.id = s.book_id and u.id = #{id} and s.date like" +
            "#{data}")
    List<Sales> getMonthly(@Param("id") Integer id,@Param("data") String date);

    /**
     * 获得一年的销量
     *
     * @param id   id
     * @param date 日期
     * @return {@link List}<{@link Sales}>
     */
    @Select("SELECT b.`name`,s.sales " +
            "FROM sales s,user u,book b,user_book ub " +
            "WHERE u.id = ub.user_id and ub.book_id = b.id and b.id = s.book_id and u.id = #{id} and s.date like " +
            "#{data}")
    List<Sales> getYear(@Param("id") Integer id,@Param("data") String date);

    /**
     * 获得商品销售额
     *
     * @param id   id
     * @param date 日期
     * @return {@link List}<{@link CommodityMarketingAmount}>
     */
    @Select("select ma.marketing_amount,ma.commodity_code,ma.`name` " +
            "from user u,user_book ub,book b,commodity_marketing_amount ma " +
            "WHERE u.id = ub.user_id and ub.book_id = b.id and b.commodity_code = ma.commodity_code and u.id = #{id} and ma.`data` like #{data}")
    List<CommodityMarketingAmount> getCommodityMarketingAmount(@Param("id") Integer id, @Param("data") String date);


    /**
     * 得到月销售额
     *
     * @param id   id
     * @param date 日期
     * @return {@link String}
     */
    @Select("select SUM(ma.marketing_amount) " +
            "from user u,user_book ub,book b,commodity_marketing_amount ma " +
            "WHERE u.id = ub.user_id and ub.book_id = b.id and b.commodity_code = ma.commodity_code and u.id = #{id} and ma.`data` like  #{data}")
    String getMonthMarketingAmount(@Param("id") Integer id,@Param("data") String date);
}

