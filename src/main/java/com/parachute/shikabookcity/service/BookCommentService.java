package com.parachute.shikabookcity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.shikabookcity.entity.Book;
import com.parachute.shikabookcity.entity.BookComment;

import java.util.List;

/**
 * (BookComment)表服务接口
 *
 * @author machi
 * @since 2022-04-15 23:57:02
 */
public interface BookCommentService extends IService<BookComment> {

    /**
     * 获取书籍
     *
     * @param userName 用户名
     * @return {@link List}<{@link Book}>
     */
    List<Book> getBook(String userName);

    /**
     * 获取书籍评论
     *
     * @param userName 用户名
     * @return {@link List}<{@link BookComment}>
     */
    List<BookComment> getBookComment(String userName);
}

