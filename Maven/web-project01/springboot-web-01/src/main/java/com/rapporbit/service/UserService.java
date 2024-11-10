package com.rapporbit.service;
 import com.rapporbit.pojo.User;
import java.util.List;

public interface UserService {

    /**
     * 查询所有用户信息
     */
    public List<User> findAll();
}
