package com.jutixueyuan.mapper;

import com.jutixueyuan.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-02-07 14:40
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Mapper
public interface EmpMapper {

    /**
     *  查询部门的员工
     * @param did
     * @return
     */
    List<Emp> findEmp(Integer did);

}
