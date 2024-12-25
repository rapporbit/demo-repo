package com.rapporbit.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ClassHasStudentsException extends RuntimeException {

    public ClassHasStudentsException() {
        super("对不起, 该班级下有学生, 不能直接删除");
    }
}