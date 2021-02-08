package com.jutixueyuan.service;

import com.jutixueyuan.mapper.DeptMapper;
import com.jutixueyuan.mapper.EmpMapper;
import com.jutixueyuan.pojo.Dept;
import com.jutixueyuan.pojo.Emp;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-02-07 14:47
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   这里需要把 service服务配置到 dubbo中 (添加到zookeeper注册中)
 */
@DubboService
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findDept() {
        return deptMapper.findDept();
    }
}