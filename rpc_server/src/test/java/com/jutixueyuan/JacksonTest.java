package com.jutixueyuan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jutixueyuan.bean.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄药师
 * @date 2021-02-06 10:42
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   jackSon的测试
 *
 *     springmvc中 默认使用json的序列化工具是 jackson
 *
 *     类似从产品是 fastson / Gson
 *
 *     springbootweb整合中
 *           默认集成了  jackSon
 *     他的使用和 fast 基本都是差不多
 *        ObjectMapper 对象
 *             提供了很多方法 转成 json字符串   writeValueAsString
 *             把json字符串转成 java对象       readValue
 *
 *    springmvc中 直接集成了   jackSon 方法的调用不用我们自己处理
 *        @ResponseBody  注解把 对象转成json字符串
 *        @RequestBody   注解把 把json字符串 转成了java对象
 *
 */
public class JacksonTest {

    @Test
    public void test01() throws JsonProcessingException {

        // 创建对象映射对象
        ObjectMapper objMap = new ObjectMapper();

        User user = new User();
        user.setName("scott");
        user.setAge(30);

        String s = objMap.writeValueAsString(user);
        System.out.println("s = " + s);

    }

    /**
     *  把json字符串转成 java对象
     */
    @Test
    public void test02() throws JsonProcessingException {

        ObjectMapper objMap = new ObjectMapper();

        String jsonStr = "{\"name\":\"scott\",\"age\":30}";

        User user = objMap.readValue(jsonStr, User.class);

        System.out.println("user = " + user);
    }

    @Test
    public void test03() throws JsonProcessingException {

        // 创建对象映射对象
        ObjectMapper objMap = new ObjectMapper();

        User user = new User();
        user.setName("scott");
        user.setAge(30);

        User user2= new User();
        user2.setName("admin");
        user2.setAge(40);

        // 把多个对象添加到集合中
        List<User> list = new ArrayList<>();

        list.add(user);
        list.add(user2);

        String s = objMap.writeValueAsString(list);
        System.out.println("s = " + s);

    }

    /**
     *  把json 字符串 数据转成   List<User>
     *      new TypeReference<List<User>>(){}
     *  给引用类型,  需要转成指定的集合 给到泛型中就可以
     *
     * @throws JsonProcessingException
     */
    @Test
    public void test04() throws JsonProcessingException {

        // 创建对象映射对象
        ObjectMapper objMap = new ObjectMapper();

        String jsonStr = " [{\"name\":\"scott\",\"age\":30},{\"name\":\"admin\",\"age\":40}]";

        // 给一个类型引用  TypeReference  这个类型应用是一个接口  直接new 给泛型就可以
        List<User> users = objMap.readValue(jsonStr, new TypeReference<List<User>>() {
        });

        System.out.println("users = " + users);
    }
}
