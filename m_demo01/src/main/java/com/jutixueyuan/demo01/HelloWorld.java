package com.jutixueyuan.demo01;

/**
 * @author 黄药师
 * @date 2021-01-19 14:37
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *     maven 项目运行:
 *       会进行编译
 *         产生  target 目录  编译后的java代码和打包的jar
 *              classes  java编译后的代码
 *
 *     01 clean，清除命令，作用清除已经编译的class文件和war文件
 *        把 target 目录进行 清除
 *
 *     02 compile，编译命令,作用是将java文件编译成class文件
 *
 *     03 package，打包命令，作用将class文件打成war包(非web项目打成jar包)
 *
 *       打包后的jar/war 放到 target 最后的一个位置
 *
 *     04 test，测试命令，作用执行Junit工具（可以忽略）
 *
       05   install，安装命令，一条命令包括了,clean complile package test
         打包 把jar 放到 本地仓库中
 *
 */
public class HelloWorld {

    public static void main(String[] args) {

        System.out.println("HelloWorld.main");

    }

}
