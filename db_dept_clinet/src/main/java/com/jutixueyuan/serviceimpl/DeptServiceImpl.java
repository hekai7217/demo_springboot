package com.jutixueyuan.serviceimpl;

import com.jutixueyuan.pojo.Dept;
import com.jutixueyuan.service.DeptService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-02-07 14:57
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Service
public class DeptServiceImpl implements DeptService {

    /**
     *  远程调用
     *
     *    从dubbo中获取远程调用
     *
     */
    @DubboReference
    private DeptService deptService;

    @Override
    public List<Dept> findDept() {
        return deptService.findDept();
    }
}
