package com.jutixueyuan.service;

import com.jutixueyuan.pojo.Emp;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-02-07 15:37
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Service
public class EmpServiceImpl implements  EmpService{

    @DubboReference
    private EmpService empService;

    @Override
    public List<Emp> findEmp(Integer did) {
        return empService.findEmp(did);
    }
}
