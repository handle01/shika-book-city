package com.parachute.shikabookcity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.shikabookcity.entity.Book;
import com.parachute.shikabookcity.util.Result;

import java.util.List;
import java.util.Map;

/**
 * 书服务
 * (Book)表服务接口
 *
 * @author machi
 * @date 2022/04/22
 * @since 2022-04-18 17:30:52
 */
public interface BookService extends IService<Book> {

    /**
     * 获取上架书籍
     *
     * @param userName 用户名
     * @return {@link List}<{@link Book}>
     */
    List<Book> getIsAdded(String userName);

    /**
     * 删除
     *
     * @param userId 用户id
     * @param bookId 书id
     * @return boolean
     */
    void delById(Integer userId,Integer bookId);

    /**
     * 添加
     *
     * @param userId 用户id
     * @param bookId 书id
     *
     */
    void addUserBook(Integer userId,Integer bookId);

    /**
     * 获取下架书籍
     *
     * @param userName 用户名
     * @return {@link List}<{@link Book}>
     */
    List<Book> getNoAdded(String userName);


    /**
     * 添加图书类型
     *
     * @param id   bookId
     * @param type 类型
     */
    void addBookType(Integer id, String type);

    /**
     * 删除图书类型
     *
     * @param id bookId
     */
    void delBookType(Integer id);

    /**
     * 验证表单
     *
     * @param book 书
     * @return {@link Result}
     */
    Result validateForm(Book book);

    /**
     * 更新
     *
     * @param book 书
     */
    void update(Book book);

    /**
     * 插入
     *
     * @param data 数据
     * @return {@link Result}
     */
    Result insert(Map<String,Object> data);

    /**
     * 插入不重复商品编码
     *
     * @return {@link String}
     */
    String insertCommodityCode();
}

