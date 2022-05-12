package com.parachute.shikabookcity.controller;


import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.parachute.shikabookcity.constant.ResultConstant;
import com.parachute.shikabookcity.entity.Book;
import com.parachute.shikabookcity.entity.BookComment;
import com.parachute.shikabookcity.service.BookCommentService;
import com.parachute.shikabookcity.util.Page;
import com.parachute.shikabookcity.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BookComment)表控制层
 *
 * @author makejava
 * @since 2022-04-15 23:57:01
 */
@Slf4j
@RestController
@RequestMapping("bookComment")
public class BookCommentController {
    /**
     * 服务对象
     */
    @Resource
    private BookCommentService bookCommentService;

    /**
     * 获取书籍
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping("getBook")
    public Result getBook(Page page){
        PageMethod.startPage(page.getCurrent(),page.getSize());
        try {
            List<Book> books = bookCommentService.getBook(page.getUserName());
            PageInfo<Book> pageInfo = new PageInfo<>(books);
            return Result.of(true,"",pageInfo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, ResultConstant.SERVER_EXCEPTION);
        }

    }

    /**
     * 获取书籍评论
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping("getBookComment")
    public Result getBookComment(Page page){
        PageMethod.startPage(page.getCurrent(), page.getSize());
        try {
            List<BookComment> comments = bookCommentService.getBookComment(page.getUserName());
            PageInfo<BookComment> info = new PageInfo<>(comments);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }
    }

}

