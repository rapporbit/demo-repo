package com.rapporbit.controller;


import com.rapporbit.anno.LogOperation;
import com.rapporbit.pojo.PageResult;
import com.rapporbit.pojo.Result;
import com.rapporbit.pojo.Student;
import com.rapporbit.pojo.StudentQueryParam;
import com.rapporbit.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("学生列表查询");
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    @LogOperation
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("学生添加:" + student);
        studentService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("学生查询:" + id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("学生更新:" + student);
        studentService.update(student);
        return Result.success();
    }

    @LogOperation
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("学生删除:" + ids);
        studentService.delete(ids);
        return Result.success();
    }

    @LogOperation
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolationScore(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学生违纪分数更新:" + id + " " + score);
        studentService.updateViolationScore(id, score);
        return Result.success();
    }


}
