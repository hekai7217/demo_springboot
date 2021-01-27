package com.jutixueyuan.springbootdemo07thymeleaf.service;

import com.jutixueyuan.springbootdemo07thymeleaf.bean.Stu;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-22 15:42
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
public interface StuService {

    List<Stu> findStus();

    int addStu(Stu stu);

    Stu findById(Integer id);

    int updateStu(Stu stu);

    int delStu(Integer id);
}
