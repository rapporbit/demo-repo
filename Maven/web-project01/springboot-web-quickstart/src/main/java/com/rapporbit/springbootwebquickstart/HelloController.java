package com.rapporbit.springbootwebquickstart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 通过该注解，将该类标记为一个可接受HTTP请求的控制器
public class HelloController {
    // 通过该注解，将该方法标记为一个请求处理方法
    // 该方法返回一个字符串，字符串内容为"Hello, Spring Boot!"
    // 该方法将处理对"/hello"的GET请求
    // 该方法将返回一个字符串，字符串内容为"Hello, Spring Boot!"
    @RequestMapping("/hello")
    public String hello(String name) {
        System.out.println("name: " + name);
        return "Hello, " + name + ", Spring Boot!";
    }
}
