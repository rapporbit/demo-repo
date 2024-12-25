package com.rapporbit.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rapporbit.exception.ClassHasStudentsException;
import com.rapporbit.mapper.ClazzMapper;
import com.rapporbit.mapper.StudentMapper;
import com.rapporbit.pojo.Clazz;
import com.rapporbit.pojo.ClazzQueryParam;
import com.rapporbit.pojo.PageResult;
import com.rapporbit.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //查询数据
        List<Clazz> list = clazzMapper.list(clazzQueryParam);
        //设置课程 stauts
        LocalDate loc = LocalDate.now();
        for (Clazz clazz : list) {
            if (loc.isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else if (loc.isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        }
        //创建 pagehelper 分页对象
        Page<Clazz> p = (Page<Clazz>) list;


        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(Integer id) {
        int studentCount = clazzMapper.getStudentCountByClazzId(id);
        if (studentCount > 0) {
            throw new ClassHasStudentsException();
        }
        clazzMapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz get(Integer id) {
        Clazz clazz = clazzMapper.getById(id);
        return clazz;
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    @Override
    public List<Clazz> getAll() {
        List<Clazz> list = clazzMapper.getAll();
        return list;
    }
}
