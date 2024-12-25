package com.rapporbit.controller;


import com.rapporbit.pojo.ClazzOption;
import com.rapporbit.pojo.JobOption;
import com.rapporbit.pojo.Result;
import com.rapporbit.service.EmpService;
import com.rapporbit.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empGenderData")
    public Result empGenderData() {
        log.info("查询员工性别数据");
        List<Map<String, Object>> empGenderData = reportService.getEmpGenderData();
        return Result.success(empGenderData);
    }


    @GetMapping("/empJobData")
    public Result empJobData() {
        log.info("查询员工工作数据");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        log.info("查询学生学历数据");
        List<Map<String, Integer>> studentDegreeData = reportService.getStudentDegreeData();
        return Result.success(studentDegreeData);
    }

    @GetMapping("/studentCountData")
    public Result studentCountData() {
        log.info("查询学生数量数据");
        ClazzOption clazzOption = reportService.getStudentCountData();
        return Result.success(clazzOption);
    }

}
