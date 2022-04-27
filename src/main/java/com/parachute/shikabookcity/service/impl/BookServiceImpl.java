package com.parachute.shikabookcity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.shikabookcity.dao.BookDao;
import com.parachute.shikabookcity.entity.Book;
import com.parachute.shikabookcity.service.BookService;
import com.parachute.shikabookcity.util.DateUtil;
import com.parachute.shikabookcity.util.Result;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        String cover = book.getCover();//封面
        if (cover == null) {
            return Result.of(false, "没有上传图片");
        }
        String format = book.getFormat();//开本类型
        if (format == null) {
            return Result.of(false, "开本类型没有选择");
        }
        String packaging = book.getPackaging();//包装类型
        if (packaging == null) {
            return Result.of(false, "包装类型没有选择");
        }
        String thePaper = book.getThePaper();//用纸类型
        if (thePaper == null) {
            return Result.of(false, "用纸类型没有选择");
        }
        String isbn = book.getIsbn();//ISBN、
        //正则判断字符串有没有字母存在
        String regex = "^\\d+$";
        if (!isbn.matches(regex)) {

            return Result.of(false, "ISBN必须全是数字");
        }

        String description = book.getDescription();//简介
        if (description == null || description.length() == 0) {
            return Result.of(false, "简介为空");
        }
        String publication = book.getPublicationTime();//出版时间
        if (publication == null) {
            return Result.of(false, "出版时间未选择");
        }
        Date publicationTime = DateUtil.String2Date(publication, "yyyy-MM-dd");

        if (publicationTime.compareTo(new Date()) > 0) {
            return Result.of(false, "出版时间不能大于当前时间");
        }
        if (book.getType() == null) {
            return Result.of(false, "类型没有选择");
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
                .set(Book::getUpdateName, "用户本人");
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
    public Result insert(Map data) {
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
        //随机生成商品编码
        int i = new Random().nextInt(100000000);
        String commodityCode = Integer.toString(i);
        book.setCommodityCode(commodityCode);
        //填入基本信息
        book.setCreateTime(new Date());
        book.setUpdateName("用户本人");
        book.setUpdateTime(new Date());
        save(book);
        int i1 = Integer.parseInt(id);
        //修改用户与书籍的联系
        addUserBook(i1, book.getId());
        List<String> types = book.getType();
        //注入书籍类型联系
        Integer bookId = book.getId();
        types.forEach(type -> bookDao.addBookType(bookId, type));
        return Result.of(true,"添加成功");
    }

    /**
     * 通过id删除书籍
     *
     * @param userId 用户id
     * @param bookId 书id
     * @return boolean
     */
    @Override
    public boolean delById(Integer userId, Integer bookId) {

        try {
            bookDao.delById(bookId);
            bookDao.delUserBook(userId, bookId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 添加用户所以书籍
     *
     * @param userId 用户id
     * @param bookId 书id
     * @return boolean
     */
    @Override
    public boolean addUserBook(Integer userId, Integer bookId) {
        try {
            bookDao.addUserBook(userId, bookId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

