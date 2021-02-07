package com.jutixueyuan.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author 黄药师
 * @date 2021-02-07 10:38
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Service
public class DubboService implements DemoService{

    /**
     *  这里的服务器是 去 注册中找  远程服务器
     *
     *      @DubboReference  dubbo 的远程引用
     *
     */
    @DubboReference
    private DemoService demoService;

    @Override
    public String sayDubbo(String name) {
        return demoService.sayDubbo(name);
    }
}
