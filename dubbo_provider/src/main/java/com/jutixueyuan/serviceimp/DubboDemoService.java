package com.jutixueyuan.serviceimp;

import com.jutixueyuan.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 黄药师
 * @date 2021-02-07 9:50
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *  把服务器注册到 zookeeper 中
 *
 *   2.7.7 之前使用
 *   org.apache.dubbo.config.annotation.Service; 这个注解是 把 服务注册到zookeeper中 低版本的dubbo使用的
 *
 *   高版本的dubbo这个注解用 @DubboService 替换的   2.7.7
 *
 *
 *   配置负载均衡的轮询
 *      loadbalance = "roundrobin"
 */
@DubboService(loadbalance = "roundrobin")
public class DubboDemoService implements DemoService {
    @Override
    public String sayDubbo(String name) {

        System.out.println("DubboDemoService 远程 dubbo 调用了 20883");
        return "dubbo " + name;
    }
}
