package com.jutixueyuan.springbootdemo07thymeleaf.service;

import com.jutixueyuan.springbootdemo07thymeleaf.bean.Stu;
import com.jutixueyuan.springbootdemo07thymeleaf.mapper.StuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-22 15:43
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

    @Override
    public Stu findById(Integer id) {
        return stuMapper.findById(id);
    }

    @Override
    public int updateStu(Stu stu) {

        return stuMapper.updateStu(stu);
    }

    @Override
    public int delStu(Integer id) {
        return stuMapper.delStu(id);
    }
}
