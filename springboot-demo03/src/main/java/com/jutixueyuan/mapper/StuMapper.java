package com.jutixueyuan.mapper;

import com.jutixueyuan.bean.Stu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-20 16:56
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *  自动配置 没有把mapper注入到ioc容器中
 *   用注解把对象注入ioc容器中
 *
 *     @Repository   Spring的注解    dao层
 *     @Mapper       mybatis的注解   标记mapper
 *
 */
@Mapper
public interface StuMapper {

    List<Stu> findStus();

    int addStu(Stu stu);

}
