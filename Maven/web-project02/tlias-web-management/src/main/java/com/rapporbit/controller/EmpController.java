package com.rapporbit.controller;

import com.rapporbit.anno.LogOperation;
import com.rapporbit.pojo.Emp;
import com.rapporbit.pojo.EmpQueryParam;
import com.rapporbit.pojo.PageResult;
import com.rapporbit.pojo.Result;
import com.rapporbit.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;


//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1" ) Integer page,
//                       @RequestParam(defaultValue = "10" ) Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        log.info("查询员工列表：page={},pageSize={},name={},gender={},begin={},end={}",page,pageSize,name,gender,begin,end);
//        PageResult pageResult = empService.page(page, pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询员工列表：page={},pageSize={},name={},gender={},begin={},end={}",empQueryParam);
        PageResult pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @LogOperation
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("保存员工信息:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    @LogOperation
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工信息:{}",ids);
        empService.delete(ids);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工信息：id={}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<Emp> list = empService.list();
        return Result.success(list);
    }
}
