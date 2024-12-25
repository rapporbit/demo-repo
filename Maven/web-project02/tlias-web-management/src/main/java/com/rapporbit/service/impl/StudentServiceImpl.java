package com.rapporbit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rapporbit.mapper.StudentMapper;
import com.rapporbit.pojo.PageResult;
import com.rapporbit.pojo.Student;
import com.rapporbit.pojo.StudentQueryParam;
import com.rapporbit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> std = studentMapper.page(studentQueryParam);
        Page<Student> stdPage = (Page<Student>) std;
        return new PageResult<Student>(stdPage.getTotal(), stdPage.getResult());
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    @Override
    public Student getById(Integer id) {
        Student student = studentMapper.getById(id);
        return student;
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void updateViolationScore(Integer id, Integer score) {
        studentMapper.updateViolationScore(id, score);
    }
}
