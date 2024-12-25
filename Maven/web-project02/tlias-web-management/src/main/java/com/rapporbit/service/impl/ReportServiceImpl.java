package com.rapporbit.service.impl;

import com.rapporbit.mapper.EmpMapper;
import com.rapporbit.mapper.StudentMapper;
import com.rapporbit.pojo.ClazzOption;
import com.rapporbit.pojo.JobOption;
import com.rapporbit.service.EmpService;
import com.rapporbit.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> empJobData = empMapper.getEmpJobData();
        List<Object> jobList = empJobData.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = empJobData.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        List<Map<String, Object>> empGenderData = empMapper.getEmpGenderData();
        return empGenderData;
    }

    @Override
    public List<Map<String, Integer>> getStudentDegreeData() {
        List<Map<String, Integer>> studentDegreeData = studentMapper.getStudentDegreeData();
        return studentDegreeData;
    }

    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String, Object>> studentCountData = studentMapper.getStudentCountData();
        List<Object> clazzList = studentCountData.stream().map(dataMap -> dataMap.get("name")).toList();
        List<Object> dataList = studentCountData.stream().map(dataMap -> dataMap.get("value")).toList();
        return new ClazzOption(clazzList, dataList);
    }
}
