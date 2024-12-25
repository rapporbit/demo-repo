package com.rapporbit.controller;


import com.rapporbit.anno.LogOperation;
import com.rapporbit.pojo.Clazz;
import com.rapporbit.pojo.ClazzQueryParam;
import com.rapporbit.pojo.PageResult;
import com.rapporbit.pojo.Result;
import com.rapporbit.service.ClazzService;
import com.rapporbit.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    ClazzService clazzService;

    //分页查询
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询班级列表：page={},pageSize={},name={},begin={},end={}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @LogOperation
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级信息：id={}", id);
        clazzService.delete(id);
        return Result.success();
    }

    @LogOperation
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级信息：{}", clazz);
        clazzService.add(clazz);
        return Result.success();

    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("查询班级信息：id={}", id);
        Clazz clazz = clazzService.get(id);
        return Result.success(clazz);
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("更新班级信息：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAll() {
        log.info("查询所有班级信息");
        List<Clazz> list = clazzService.getAll();
        return Result.success(list);
    }
}
