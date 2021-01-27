package com.jutixueyuan;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jutixueyuan.bean.Stu;
import com.jutixueyuan.mapper.StuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-20 17:00
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@SpringBootTest
public class StuMapperTest {

    @Autowired
    private StuMapper stuMapper;

    @Test
    public void test(){

        System.out.println("stuMapper = " + stuMapper);

    }

    @Test
    public void test02(){
        System.out.println("stuMapper = " + stuMapper.findStus());
    }

    /**
     * 测试分页
     */
    @Test
    public void test03(){

        // 01 设置分页
        /**
         *  设置参数
         *    pageNum    当前页面
         *    pageSize   页面大小
         */
        PageHelper.startPage(1,3);

        // 02 查询你要分页的结果
        List<Stu> stus = stuMapper.findStus();

        // 03 设置分页

        PageInfo<Stu> stuPageInfo = new PageInfo<>(stus);

        System.out.println("stuPageInfo = " + stuPageInfo);

        // 分页数据的封装的结果集
        List<Stu> list = stuPageInfo.getList();
        System.out.println("list = " + list);


        System.out.println("总数:" + stuPageInfo.getTotal());
        System.out.println("总页数:" + stuPageInfo.getPages());


    }


}
