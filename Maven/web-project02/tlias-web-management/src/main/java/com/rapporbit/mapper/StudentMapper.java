package com.rapporbit.mapper;

import com.rapporbit.pojo.PageResult;
import com.rapporbit.pojo.Student;
import com.rapporbit.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> page(StudentQueryParam studentQueryParam);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void updateViolationScore(Integer id, Integer score);

    @MapKey("degree")
    List<Map<String, Integer>> getStudentDegreeData();

    @MapKey("name")
    List<Map<String, Object>> getStudentCountData();
}
