package com.rapporbit.service.impl;

import com.rapporbit.dao.UserDao;
import com.rapporbit.dao.impl.UserDaoImpl;
import com.rapporbit.pojo.User;
import com.rapporbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired //自动注入,应用程序运行时，会自动的查询改类型的bean对象，并负值给改成员变量
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        //1.调用 Dao
        List<String> lines = userDao.findAll();
        //2.解析用户数据，封装为 User 对象 -> list集合
        List<User> userList = lines.stream().map(
                line -> {
                    String[] parts = line.split(",");
                    return new User(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
        ).toList();
        return userList;
    }
}
