package com.rapporbit.service;

import com.rapporbit.pojo.ClazzOption;
import com.rapporbit.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Integer>> getStudentDegreeData();

    ClazzOption getStudentCountData();
}
