package com.jutixueyuan.serviceimpl.impl;

import com.jutixueyuan.mapper.StuMapper;
import com.jutixueyuan.pojo.Stu;
import com.jutixueyuan.serviceimpl.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-19 15:59
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public List<Stu> findStus() {
        return stuMapper.findStus();
    }

    @Override
    public int addStu(Stu stu) {
        return stuMapper.addStu(stu);
    }

}
