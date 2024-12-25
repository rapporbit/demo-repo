package com.rapporbit.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DeptHasDeleteException extends RuntimeException {
    public DeptHasDeleteException() {
        super("对不起，当前部门下有员工，不能直接删除！");
    }
}
