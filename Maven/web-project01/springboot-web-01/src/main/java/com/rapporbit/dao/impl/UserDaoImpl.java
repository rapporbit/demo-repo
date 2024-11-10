package com.rapporbit.dao.impl;

import cn.hutool.core.io.IoUtil;
import com.rapporbit.dao.UserDao;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public List<String> findAll() {
        //1.加载并读取 user.txt文件，获取用户数据
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
