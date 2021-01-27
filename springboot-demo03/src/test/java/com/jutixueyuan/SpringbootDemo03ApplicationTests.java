package com.jutixueyuan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 *  @SpringBootTest
 *    01 可以 启动ioc容器
 *    02 可以把其他的组件注入进来
 *
 *   以前:@RunWith(SpringJunit4Runner.Class)
 *   升级为 junit 5.x 不用使用这个 使用@ExtendWith(SpringExtension)
 *   @SpringBootTest 直接 启动和可以注入组件
 */
@SpringBootTest
class SpringbootDemo03ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void test(){

        System.out.println("dataSource = " + dataSource);

        try {
            System.out.println(dataSource.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
