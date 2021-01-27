package com.jutixueyuan.service;

import com.jutixueyuan.pojo.Stu;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-19 15:59
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
public interface StuService {

    List<Stu> findStus();

    int addStu(Stu stu);

}
