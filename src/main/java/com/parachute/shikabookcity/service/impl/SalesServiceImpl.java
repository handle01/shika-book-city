package com.parachute.shikabookcity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.shikabookcity.dao.SalesDao;
import com.parachute.shikabookcity.entity.CommodityMarketingAmount;
import com.parachute.shikabookcity.entity.Sales;
import com.parachute.shikabookcity.service.SalesService;
import com.parachute.shikabookcity.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * (Sales)表服务实现类
 *
 * @author machi
 * @since 2022-04-15 23:57:06
 */
@Service("salesService")
public class SalesServiceImpl extends ServiceImpl<SalesDao, Sales> implements SalesService {

    @Autowired
    private SalesDao salesDao;

    /**
     * 获取当天销量
     *
     * @param id id
     * @return {@link List}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    @Override
    public List<HashMap<String,Object>> getDaily(Integer id) {
        Date date = new Date();
        String s;
        s = DateUtils.date2String(date);
        //获取“2022-04-26”格式日期
        String[] s1 = s.split(" ");
        List<Sales> daily = salesDao.getDaily(id, s1[0]);
        return autoData(daily);
    }

    /**
     * 获取当月销量
     *
     * @param id id
     * @return {@link List}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    @Override
    public List<HashMap<String, Object>> getMonthly(Integer id) {
        Date date = new Date();
        String s;
        s = DateUtils.date2String(date);
        String[] s1 = s.split(" ");
        String[] split = s1[0].split("-");
        //获取“2022-04%”格式
        String tine = split[0]+ "-" + split[1] +"%";

        List<Sales> daily = salesDao.getMonthly(id, tine);
        return autoData(daily);

    }

    /**
     * 获得一年的销量
     * @param id id
     * @return {@link List}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    @Override
    public List<HashMap<String, Object>> getYear(Integer id) {

        Date date = new Date();
        String s;
        s = DateUtils.date2String(date);
        String[] s1 = s.split(" ");
        String[] split = s1[0].split("-");
        //获取“2022%”格式
        String tine = split[0] + "%";
        //获取年销量
        List<Sales> daily = salesDao.getYear(id, tine);
        return autoData(daily);
    }

    /**
     * 获得商品销售额
     *
     * @param id id
     * @return {@link List}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    @Override
    public List<HashMap<String, Object>> getCommodityMarketingAmount(Integer id) {
        Date date = new Date();
        String s;
        s = DateUtils.date2String(date);
        String[] s1 = s.split(" ");
        String[] split = s1[0].split("-");
        //获取“2022-04%”格式
        String tine = split[0]+ "-" + split[1] +"%";

        List<CommodityMarketingAmount> daily = salesDao.getCommodityMarketingAmount(id, tine);
        List<HashMap<String,Object>> list = new ArrayList<>();
        //返回书本与销量对应数据
        daily.forEach(commodity -> {
            HashMap<String, Object> map = new HashMap<>(16);
            map.put("value",commodity.getMarketingAmount());
            map.put("name",commodity.getName());
            list.add(map);
        });
        return list;

    }



    /**
     * 获得年度销售额
     *
     * @param id id
     * @return {@link List}<{@link List}<{@link String}>>
     */
    @Override
    public List<List<String>> getYearMarketingAmount(Integer id) {
        List<List<String>> list = new ArrayList<>();
        List<String> money = new ArrayList<>();
        List<String> data = DateUtils.getData();
        //获取前一年每月的营销额
        data.forEach(d->{
            //获取“2022%”格式
            String s = d + "%";
            String monthMarketingAmount = getMonthMarketingAmount(id, s);
            if (monthMarketingAmount != null){
                money.add(monthMarketingAmount);
            }
           money.add("0");
        });
        //返回日期与销售额对应的数据给前端
        list.add(data);
        list.add(money);
        return list;
    }

    /**
     * 得到月销售额
     *
     * @param id   id
     * @param date 日期
     * @return {@link String}
     */
    @Override
    public String getMonthMarketingAmount(Integer id,String date) {
        return salesDao.getMonthMarketingAmount(id,date);
    }



    /**
     * 装配数据
     *
     * @param daily 每天
     * @return {@link List}<{@link HashMap}<{@link String}, {@link Object}>>
     */
    private  List<HashMap<String,Object>> autoData(List<Sales> daily){
        List<HashMap<String,Object>> list = new ArrayList<>();
        //将数据打包给前端
        daily.forEach(sales -> {
            HashMap<String, Object> map = new HashMap<>(16);
            map.put("value",sales.getSales());
            map.put("name",sales.getName());
            list.add(map);
        });
        return list;
    }


}

