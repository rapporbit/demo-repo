package com.rapporbit.service;

import com.rapporbit.pojo.Emp;
import com.rapporbit.pojo.EmpQueryParam;
import com.rapporbit.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface EmpService {
//    PageResult page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end);
        PageResult<Emp> page(EmpQueryParam empQueryParam);

        void save(Emp emp);
}
