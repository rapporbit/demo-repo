package com.rapporbit.controller;


import cn.hutool.core.io.IoUtil;
import com.rapporbit.pojo.User;
import com.rapporbit.service.impl.UserServiceImpl;
import com.rapporbit.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/*
@RestController
public class UserController {

    @RequestMapping("/list")
    public List<User> list() throws FileNotFoundException {
        //1.加载并读取 user.txt文件，获取用户数据
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        //2.解析用户数据，封装为 User 对象 -> list集合
        List<User> userList = lines.stream().map(
                line -> {
                    String[] parts = line.split(",");
                    return new User(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
        ).toList();
        //3.返回数据（json）
        return userList;
    }
}
*/

@RestController
public class UserController {
    //1.属性注入,优点：代码简洁，缺点：隐藏了类之间的依赖关系，可能会破坏类的封装性
    @Qualifier("userServiceImpl")
    @Autowired //自动注入,应用程序运行时，会自动的查询改类型的bean对象，并负值给改成员变量
    //@Resource(name = "userServiceImpl2")
    private UserService userService;


    //2.构造函数注入，优点：可以保证并清晰的看到依赖关系的完整性，缺点：代码冗余
    /*
    private final UserService userService;
    @Autowired //如果类中只有一个构造函数，@Autowired可以省略
    public UserController(UserService userService) {
        this.userService = userService;
    }
     */

    //3.setter方法注入，优点：可以保证并清晰的看到依赖关系的完整性，保持了类的封装性，缺点：代码冗余
    /*
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
     */

        @RequestMapping("/list")
        public List<User> list() throws FileNotFoundException {
            //1.调用 Service, 获取数据
            List<User> userList = userService.findAll();
            //2.返回数据（json）
            return userList;
        }
}