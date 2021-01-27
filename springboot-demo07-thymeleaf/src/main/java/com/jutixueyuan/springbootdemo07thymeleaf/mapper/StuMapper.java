package com.jutixueyuan.springbootdemo07thymeleaf.mapper;

import com.jutixueyuan.springbootdemo07thymeleaf.bean.Stu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-22 15:39
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Mapper
public interface StuMapper {

    List<Stu> findStus();

    int addStu(Stu stu);

    Stu findById(int id);

    int updateStu(Stu stu);

    int delStu(int id);


}
