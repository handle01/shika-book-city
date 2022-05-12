package com.parachute.shikabookcity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.shikabookcity.entity.Book;
import com.parachute.shikabookcity.entity.BookComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (BookComment)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-15 23:57:02
 */
@Mapper
public interface BookCommentDao extends BaseMapper<BookComment> {

    /**
     * 获取书籍
     *
     * @param userName 用户名
     * @return {@link List}<{@link Book}>
     */
    @Select("SELECT b.cover,b.name,b.brand,b.author,b.commodity_code " +
            "from user u,user_book ub,book b " +
            "WHERE u.id = ub.user_id and ub.del_flag = 0 and ub.book_id = b.id and u.user_name = #{userName}")
    List<Book> getBook(String userName);

    /**
     * 获取书籍评论
     *
     * @param commodityCode 商品编码
     * @return {@link List}<{@link BookComment}>
     */
    @Select("select `profile`,`nick_name`,`comment`,comment_time,user_name " +
            "from book_comment " +
            "WHERE commodity_code = #{commodityCode}")
    List<BookComment> getBookComment(String commodityCode);

}

