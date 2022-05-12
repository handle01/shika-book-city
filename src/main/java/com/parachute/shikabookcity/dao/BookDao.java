package com.parachute.shikabookcity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.shikabookcity.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 书刀
 *
 * @author machi
 * @date 2022/04/25
 */
@Mapper
public interface BookDao extends BaseMapper<Book> {
    /**
     *获取上架书籍
     *
     * @param userName 用户名
     * @return {@link List}<{@link Book}>
     */
    @Select("select b.id,b.name,b.ISBN,b.publication_time,b.brand,b.press,b.price,b.author,b.packaging,b.the_paper,b.format,b.cover,b.description,b.is_added " +
            "from user u,user_book ub,book b " +
            "WHERE u.id = ub.user_id and ub.book_id = b.id and b.is_added = 1 and b.del_flag = 0 and ub.del_flag = 0 and u.user_name = #{userName}")
    List<Book> getIsAdded(@Param("userName") String userName);

    /**
     * 通过id删除书籍
     *
     * @param id id
     */
    @Update("UPDATE book b set b.del_flag = 1 WHERE b.id = #{id}")
    void delById(@Param("id") Integer id);

    /**
     * 删除用户书籍
     *
     * @param userId 用户id
     * @param bookId 书id
     */
    @Insert("UPDATE user_book ub set ub.del_flag = 1 WHERE ub.user_id = #{userId} and ub.book_id = #{bookId}")
    void delUserBook(@Param("userId") Integer userId,@Param("bookId") Integer bookId);

    /**
     * 添加用户书
     *
     * @param userId 用户id
     * @param bookId 书id
     */
    @Insert("INSERT INTO user_book (user_id,book_id) VALUES(#{userId},#{bookId})")
    void addUserBook(@Param("userId") Integer userId,@Param("bookId") Integer bookId);

    /**
     * 获取下架书籍
     *
     * @param userName 用户名
     * @return {@link List}<{@link Book}>
     */
    @Select("select b.id,b.name,b.ISBN,b.publication_time,b.brand,b.press,b.price,b.author,b.packaging,b.the_paper,b.format,b.cover,b.description,b.is_added " +
            "from user u,user_book ub,book b " +
            "WHERE u.id = ub.user_id and ub.book_id = b.id and b.is_added = 0 and b.del_flag = 0 and ub.del_flag = 0 and u.user_name = #{userName}")
    List<Book> getNoAdded(String userName);

    /**
     * 添加书籍类型
     *
     * @param id   id
     * @param type 类型
     */
    @Insert("INSERT INTO book_type (book_id,type) VALUES(#{id},#{type})")
    void addBookType(@Param("id") Integer id,@Param("type") String type);

    /**
     * 得到图书类型
     *
     * @param id id
     * @return {@link List}<{@link String}>
     */
    @Select("SELECT type FROM book_type WHERE book_id = #{id}")
    List<String> getBookType(@Param("id") Integer id);

    /**
     * 删除书籍类型
     *
     * @param id id
     */
    @Delete("DELETE FROM book_type WHERE book_id = #{id}")
    void delBookType(@Param("id") Integer id);

    /**
     * 插入商品编码防止重复
     *
     * @param commodityCode 商品编码
     */
    @Insert("INSERT into commodity_code (commodity_code) VALUES(#{commodityCode})")
    void insertCommodityCode(String commodityCode);
}

