package com.jutixueyuan.springbootdemo07thymeleaf.bean;

/**
 * @author 黄药师
 * @date 2021-01-20 16:55
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
public class Stu {

    private Integer id;

    private String name;
    private Integer age;

    private  String info;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", info='" + info + '\'' +
                '}';
    }
}
