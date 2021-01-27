package com.jutixueyuan.bean;

/**
 * @author 黄药师
 * @date 2021-01-26 15:39
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
public class Stu {

    private int id;
    private String name;
    private String info;
    private Integer age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Stu(int id, String name, String info, Integer age) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.age = age;
    }

    public Stu() {
    }
}
