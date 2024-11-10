package com.rapporbit;

import com.rapporbit.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {

    /**
     * JDBC入门程序
     */
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web01", "root", "zjlzjl02");
        // 3. 获取 SQL 语句执行对象
        Statement statement = connection.createStatement();
        // 4. 执行 SQL 语句
        int i = statement.executeUpdate("update user set age = 25 where id = 1");
        System.out.println("SQL 语句执行完毕，影响的记录数为：" + i);
        // 5. 释放资源
        statement.close();
        connection.close();
    }

    @Test
    public void testSelect() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web01", "root", "zjlzjl02");

            // 3. 准备 SQL 查询
            String sql = "SELECT id, username, password, name, age FROM user WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "daqiao");
            preparedStatement.setString(2, "123456");

            // 4. 执行查询
            resultSet = preparedStatement.executeQuery();

            // 5. 处理结果集，将每一行记录封装到 User 对象中
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );

                // 6. 输出 User 对象的数据
                System.out.println(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 7. 释放资源
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}