package com.rapporbit.service.impl;

import com.rapporbit.exception.DeptHasDeleteException;
import com.rapporbit.mapper.DeptMapper;
import com.rapporbit.pojo.Dept;
import com.rapporbit.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        Integer empCount = deptMapper.empCountByDeptId(id);
        if (empCount > 0) {
            throw new DeptHasDeleteException();
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        //设置创建时间和更新时间
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public Dept findById(Integer id) {
        Dept dept = deptMapper.findById(id);
        return dept;
    }


    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
