package com.chth.playground.dao;

import chth.playground.front.FrontApplication;
import chth.playground.front.dao.UserDao;
import chth.playground.front.datasource.ForkDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrontApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"default"})
public class UserDaoTestCases {

    @Autowired
    private UserDao userDao;

    @Test
    public void test1() {
        ForkDataSource.setDataSourceKey(ForkDataSource.SPARE);
        System.out.println(userDao.findByUserName("大程子-1991"));
        ForkDataSource.toDefault();
        System.out.println(userDao.findByUserName("大程子-1991"));
    }

    @Test
    public void test2() {
        System.out.println(userDao.findByUserName("大程子-1991"));
    }

    @Test
    public void test3() {
        List<Map<String, Object>> all = userDao.findAll();
        all.forEach(System.out::println);
    }
}
