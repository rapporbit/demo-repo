package com.rapporbit.service;

import com.rapporbit.pojo.PageResult;
import com.rapporbit.pojo.Student;
import com.rapporbit.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void updateViolationScore(Integer id, Integer score);
}
