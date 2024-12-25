package com.rapporbit.service;


import com.rapporbit.pojo.Clazz;
import com.rapporbit.pojo.ClazzQueryParam;
import com.rapporbit.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClazzService {


    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void delete(Integer id);

    void add(Clazz clazz);

    Clazz get(Integer id);

    void update(Clazz clazz);

    List<Clazz> getAll();
}
