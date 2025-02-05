package com.rapporbit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rapporbit.mapper.EmpExprMapper;
import com.rapporbit.mapper.EmpMapper;
import com.rapporbit.pojo.*;
import com.rapporbit.service.EmpLogService;
import com.rapporbit.service.EmpService;

import com.rapporbit.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    //原始实现方法
//    @Override
//    public PageResult page(Integer page, Integer pageSize) {
//        Integer start = (page - 1) * pageSize;
//        long total = empMapper.count();
//        List<Emp> rows = empMapper.list(start, pageSize);
//        return new PageResult<Emp>(total, rows);
//    }


    //基于 pagehelper 实现
//    @Override
//    public PageResult page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //设置分页参数
//        PageHelper.startPage(page, pageSize);
//        //查询数据
//        List<Emp> list = empMapper.list(name, gender, begin, end);
//        //创建 pagehelper 分页对象
//        Page<Emp> p = (Page<Emp>) list;
//        //返回结果
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//
//    }
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //查询数据
        List<Emp> list = empMapper.list(empQueryParam);
        //创建 pagehelper 分页对象
        Page<Emp> p = (Page<Emp>) list;
        //返回结果
        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }

    @Transactional //事务注解
    @Override
    public void save(Emp emp) {
        //定义时间
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp);
            empLogService.insertLog(empLog);
        }

//        //记录日志
//        EmpLog empLog = new EmpLog(null, LocalDateTime.now(),"新增员工："+ emp);
//        empLogService.insertLog(empLog);
    }

    @Transactional
    @Override
    public void delete(List<Integer> ids) {
        //1.删除员工
        empMapper.deleteByIds(ids);
        //2.删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        //1.查询员工信息
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        //1.更新员工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.删除员工工作经历
        empExprMapper.deleteByEmpIds(List.of(emp.getId()));
        //3.新增员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }

    @Override
    public List<Emp> list() {
        List<Emp> list = empMapper.getAll();
        return list;
    }

    @Override
    public LoginInfo login(Emp emp) {
        LoginInfo loginInfo = empMapper.selectByUserNameAndPassword(emp);
        if(loginInfo == null){
            return null;
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginInfo.getId());
        claims.put("username", loginInfo.getUsername());
        String token = JwtUtils.generateJwt(claims);
        loginInfo.setToken(token);
        return loginInfo;
    }

}
