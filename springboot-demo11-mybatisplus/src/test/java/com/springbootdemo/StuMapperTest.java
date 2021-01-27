package com.springbootdemo;

import com.springbootdemo.bean.User;
import com.springbootdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-23 14:37
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@SpringBootTest
public class StuMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){

        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
    }
}
