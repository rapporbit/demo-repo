package com.rapporbit.mapper;


import com.rapporbit.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //应用程序运行时，MyBatis会自动扫描该接口，并生成对应的实现类（代理对象），并将其自动将实现类对象存入 IOC 容器中
public interface UserMapper {


    /**
     * 查询所有用户
     */
    //@Select("select id,username,password,name,age from user") 在 xml文件中配置了sql语句，所以这里不需要再写sql语句
    public List<User> findAll();

    /**
     * 删除用户
     */
    @Delete("delete from user where id = #{id}") //#{}是占位符,生成的sql语句为delete from user where id = ?，预编译sql语句
    public void deleteById(Integer id);
    //public Integer deleteById(Integer id);//返回值为删除的记录数

    /**
     * 添加用户
     */
    @Insert("insert into user(username,password,name,age) values(#{username},#{password},#{name},#{age})")
    public void insert(User user);


    /**
     * 更新用户
     */
    @Update("update user set username=#{username},password=#{password},name=#{name},age=#{age} where id=#{id}")
    public void update(User user);

    /**
     * 根据username和 password 查询用户
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);//多个形参时，@Param("username") 用于给参数取别名，如果不取别名，MyBatis会默认使用参数名作为别名，导致无法识别


}
