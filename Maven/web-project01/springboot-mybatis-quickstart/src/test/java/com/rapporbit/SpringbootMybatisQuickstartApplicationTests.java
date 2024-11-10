package com.rapporbit;

import com.rapporbit.mapper.UserMapper;
import com.rapporbit.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 运行时会自动加载 Spring Boot 项目的配置类, 并自动扫描当前包及其子包, 将带有 @Component @Service @Repository @Controller 注解的类纳入 Spring 容器中进行管理
class SpringbootMybatisQuickstartApplicationTests {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll() {
        for (User user : userMapper.findAll()) {
            System.out.println(user);
        }
    }

    @Test
    public void testDeleteById() {
        userMapper.deleteById(5);
    }

    @Test
    public void testInsert() {
        User user = new User(null, "gaoyuanyuan", "123456", "高圆圆", 35);
        userMapper.insert(user);
    }

    @Test
    public void testUpdate() {
        User user = new User(1, "zhuoyu", "123456", "周瑜",  20);
        userMapper.update(user);
    }

    @Test
    public void testFindByUsernameAndPassword() {
        User user = userMapper.findByUsernameAndPassword("zhuoyu", "123456");
        System.out.println(user);
    }
}
