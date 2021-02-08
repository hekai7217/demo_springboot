package com.jutixueyuan.pojo;

import java.io.Serializable;

/**
 * @author 黄药师
 * @date 2021-02-07 14:33
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
public class Dept implements Serializable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept() {
    }

    public Dept(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
