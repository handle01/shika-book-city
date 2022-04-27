package com.parachute.shikabookcity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.shikabookcity.dao.BookCommentDao;
import com.parachute.shikabookcity.entity.Book;
import com.parachute.shikabookcity.entity.BookComment;
import com.parachute.shikabookcity.service.BookCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (BookComment)表服务实现类
 *
 * @author machi
 * @since 2022-04-15 23:57:03
 */
@Service("bookCommentService")
public class BookCommentServiceImpl extends ServiceImpl<BookCommentDao, BookComment> implements BookCommentService {

    @Autowired
    BookCommentDao bookCommentDao;

    /**
     * 获取书籍
     *
     * @param userName 用户名
     * @return {@link List}<{@link Book}>
     */
    @Override
    public List<Book> getBook(String userName) {
        return bookCommentDao.getBook(userName);
    }

    /**
     * 获取书籍评论
     *
     * @param commodityCode 商品编码
     * @return {@link List}<{@link BookComment}>
     */
    @Override
    public List<BookComment> getBookComment(String commodityCode) {
        return bookCommentDao.getBookComment(commodityCode);
    }
}

