package com.rapporbit.mapper;

import com.rapporbit.pojo.Emp;
import com.rapporbit.pojo.EmpQueryParam;
import com.rapporbit.pojo.LoginInfo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    //原始实现方法
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public long count();
//
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);


    //使用 pagehelper 插件
//    @Select("select e.*, d.name from emp e left join dept d on e.dept_id = d.id " +
//            "where e.name like concat('%',#{name},'%') and e.gender = #{gender} and e.entry_date between #{begin} and #{end} " +
//            "order by e.update_time desc")
    //public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id") //设置主键自增,并将自增的值赋值给id
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values(#{username}, #{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> getEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> getEmpGenderData();

    List<Emp> getAll();

    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    LoginInfo selectByUserNameAndPassword(Emp emp);
}
