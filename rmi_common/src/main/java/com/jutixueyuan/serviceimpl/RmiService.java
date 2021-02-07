package com.jutixueyuan.serviceimpl;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author 黄药师
 * @date 2021-02-06 14:24
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *  接口
 *
 *     完成服务器
 *         实现  RmiService 接口
 *         在 register进行注册
 *
 *     完成 客户端
 *        从register中找服务
 *        调用服务器service中实现的方法
 *
 *    定义的接口 需要继承:     Remote 接口
 *
 *
 */
public interface RmiService extends Remote {

    /**
     * 接口中需要抛出  RemoteException
     * @param msg
     * @return
     * @throws RemoteException
     */
    String sayRemote(String msg) throws RemoteException;

}
