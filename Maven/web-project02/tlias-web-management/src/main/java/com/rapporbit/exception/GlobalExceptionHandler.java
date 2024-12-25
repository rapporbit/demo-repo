package com.rapporbit.exception;

import com.rapporbit.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("系统异常", e);
        return Result.error("系统异常");
    }

    @ExceptionHandler
    public Result handDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库中已存在该记录", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String substring = message.substring(i);
        String[] errMsg = substring.split(" ");
        return Result.error("数据库中已存在该记录:" + errMsg[2]);
    }

    @ExceptionHandler(ClassHasStudentsException.class)
    public Result handleClassHasStudentsException(ClassHasStudentsException e) {
        log.error("班级内存在学生,删除班级异常", e);
        String message = e.getMessage();
        return Result.error("班级内存在学生,删除班级异常:"+ message);
    }

    @ExceptionHandler(DeptHasDeleteException.class)
    public Result handleDeptHasDeleteException(DeptHasDeleteException e) {
        log.error("部门内存在员工,删除部门异常", e);
        String message = e.getMessage();
        return Result.error("部门内存在员工,删除部门异常: "+ message);
    }

}
