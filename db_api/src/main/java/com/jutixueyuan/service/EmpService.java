package com.jutixueyuan.service;

import com.jutixueyuan.pojo.Emp;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-02-07 14:37
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
public interface EmpService {

    public List<Emp> findEmp(Integer did);

}
