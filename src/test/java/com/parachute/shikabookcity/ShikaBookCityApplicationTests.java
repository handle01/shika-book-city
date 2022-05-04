package com.parachute.shikabookcity;


import com.parachute.shikabookcity.dao.BookDao;
import com.parachute.shikabookcity.service.BookService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class ShikaBookCityApplicationTests {


    @Autowired
    BookDao bookDao;
    @Autowired
    BookService bookService;
    @Test
    public void test() throws ParseException {
        Set<String> commodityCodes = new  HashSet<>();
        for (int i = 0; i < 10; i++) {
            String s = RandomStringUtils.randomNumeric(1);
            System.out.println(s);
        }

    }
    @Test
    public void test1() throws ParseException {
        for (int i = 0; i < 5; i++) {
            String s = bookService.insertCommodityCode();
            System.out.println(s);
        }

    }



}
