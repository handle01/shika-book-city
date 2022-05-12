package com.parachute.shikabookcity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.shikabookcity.constant.ResultConstant;
import com.parachute.shikabookcity.constant.SysConstant;
import com.parachute.shikabookcity.dao.BookDao;
import com.parachute.shikabookcity.entity.Book;
import com.parachute.shikabookcity.service.BookService;
import com.parachute.shikabookcity.util.DateUtils;
import com.parachute.shikabookcity.util.Result;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (Book)表服务实现类
 *
 * @author machi
 * @date 2022/04/25
 * @since 2022-04-18 17:30:52
 */
@Service("bookService")
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements BookService {


    @Autowired
    private BookDao bookDao;

    /**
     * 获取上架书籍    *
     *
     * @param userName 用户名
     * @return {@link List}<{@link Book}>
     */
    @Override
    public List<Book> getIsAdded(String userName) {
        List<Book> isAdded = bookDao.getIsAdded(userName);
        isAdded.forEach(book -> {
            //注入类型信息
            Integer id = book.getId();
            List<String> types = bookDao.getBookType(id);
            book.setType(types);
        });
        return isAdded;
    }

    /**
     * 获取下架书籍
     *
     * @param userName 用户名
     * @return {@link List}<{@link Book}>
     */
    @Override
    public List<Book> getNoAdded(String userName) {
        List<Book> noAdded = bookDao.getNoAdded(userName);
        noAdded.forEach(book -> {
            //注入类型信息
            Integer id = book.getId();
            List<String> types = bookDao.getBookType(id);
            book.setType(types);
        });
        return noAdded;
    }

    /**
     * 添加图书类型
     *
     * @param id   id
     * @param type 类型
     */
    @Override
    public void addBookType(Integer id, String type) {
        bookDao.addBookType(id, type);
    }

    /**
     * 删除图书类型
     *
     * @param id id
     */
    @Override
    public void delBookType(Integer id) {
        bookDao.delBookType(id);
    }

    @SneakyThrows
    @Override
    public Result validateForm(Book book) {
        //校验表单
        //封面
        String cover = book.getCover();
        if (cover == null) {
            return Result.of(false, ResultConstant.NO_PICTURES_UPLOADED);
        }
        //开本类型
        String format = book.getFormat();
        if (format == null) {
            return Result.of(false, ResultConstant.FORMAT_NOT_SELECTED);
        }
        //包装类型
        String packaging = book.getPackaging();
        if (packaging == null) {
            return Result.of(false, ResultConstant.PACKAGING_NOT_SELECTED);
        }
        //用纸类型
        String thePaper = book.getThePaper();
        if (thePaper == null) {
            return Result.of(false, ResultConstant.THE_PAPER_NOT_SELECTED);
        }
        //ISBN、
        String isbn = book.getIsbn();
        //正则判断字符串有没有字母存在
        String regex = "^\\d+$";
        if (!isbn.matches(regex)) {
            return Result.of(false, ResultConstant.ISBN_MUST_IS_NUMBER);
        }
        //简介
        String description = book.getDescription();
        if (description == null || description.length() == 0) {
            return Result.of(false, ResultConstant.DESCRIPTION_IS_EMPTY);
        }
        //出版时间
        String publication = book.getPublicationTime();
        if (publication == null) {
            return Result.of(false, ResultConstant.PUBLISH_TIME_NOT_SELECTED);
        }
        Date publicationTime = DateUtils.string2Date(publication, DateUtils.DATE);

        if (publicationTime.compareTo(new Date()) > 0) {
            return Result.of(false, ResultConstant.PUBLISH_TIME_NOT_GREATER_CURRENT_TIME);
        }
        if (book.getType() == null) {
            return Result.of(false, ResultConstant.BOOK_TYPE_NOT_SELECTED);
        }
        return null;
    }

    /**
     * 更新
     *
     * @param book 书
     */
    @Override
    public void update(Book book) {
        //条件修改
        LambdaUpdateWrapper<Book> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Book::getId, book.getId()).set(Book::getAuthor, book.getAuthor())
                .set(Book::getName, book.getName())
                .set(Book::getCover, book.getCover())
                .set(Book::getFormat, book.getFormat())
                .set(Book::getPackaging, book.getFormat())
                .set(Book::getPress, book.getPress())
                .set(Book::getPrice, book.getPrice())
                .set(Book::getThePaper, book.getThePaper())
                .set(Book::getBrand, book.getBrand()).set(Book::getIsbn, book.getIsbn())
                .set(Book::getDescription, book.getDescription())
                .set(Book::getPublicationTime, book.getPublicationTime())
                .set(Book::getUpdateTime, new Date())
                .set(Book::getUpdateName, SysConstant.USER_UPDATE);
        super.baseMapper.update(book, updateWrapper);
        //添加书籍类型
        bookDao.delBookType(book.getId());
        List<String> types = book.getType();
        types.forEach(type -> bookDao.addBookType(book.getId(), type));
    }

    /**
     * 插入
     *
     * @param data 数据
     * @return {@link Result}
     */
    @Override
    public Result insert(Map<String,Object> data) {
        //前端数据转化为对象
        Object s = data.get("book");
        Book book = JSON.parseObject(JSON.toJSONString(s), new TypeReference<Book>() {
        });
        String id = (String) data.get("id");

        //表单校验
        Result result = validateForm(book);
        if (result != null) {
            return result;
        }
        //随机生成不重复的商品编码
        String commodityCode = insertCommodityCode();
        book.setCommodityCode(commodityCode);

        //填入基本信息
        book.setCreateTime(new Date());
        book.setUpdateName(SysConstant.USER_UPDATE);
        book.setUpdateTime(new Date());
        save(book);
        int i1 = Integer.parseInt(id);
        //修改用户与书籍的联系
        addUserBook(i1, book.getId());
        List<String> types = book.getType();
        //注入书籍类型联系
        Integer bookId = book.getId();
        types.forEach(type -> bookDao.addBookType(bookId, type));
        return Result.of(true,ResultConstant.UPDATE_SUCCEED);
    }

    /**
     * 通过id删除书籍
     *
     * @param userId 用户id
     * @param bookId 书id
     * @return boolean
     */
    @Override
    public void delById(Integer userId, Integer bookId) {
            bookDao.delById(bookId);
            bookDao.delUserBook(userId, bookId);
    }

    /**
     * 添加用户所以书籍
     *
     * @param userId 用户id
     * @param bookId 书id
     *
     */
    @Override
    public void addUserBook(Integer userId, Integer bookId) {
        try {
            bookDao.addUserBook(userId, bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入重复商品编码
     *
     * @return {@link String}
     */
    @Override
    public String insertCommodityCode(){
        //随机生成商品编码
        String commodityCode = null;
        boolean flag  =false;
       while (Boolean.FALSE.equals(flag)){
           commodityCode = RandomStringUtils.randomNumeric(8);
           try {
               bookDao.insertCommodityCode(commodityCode);
               flag = true;
           }catch (Exception e){
               log.error(ResultConstant.COMMODITY_CODE_IS_DUPLICATION);
               e.printStackTrace();
           }
       }
       return commodityCode;

    }


}

