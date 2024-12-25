package com.rapporbit.controller;


import com.rapporbit.anno.LogOperation;
import com.rapporbit.pojo.Dept;
import com.rapporbit.pojo.Result;
import com.rapporbit.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list() {
        //System.out.println("查询所有部门");
        log.info("查询所有部门");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //方法一基于 HttpServletRequest 获取请求参数，用的不多，繁琐
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("删除部门"+ id);
//        return Result.success();
//    }

        //方法二基于 @RequestParam 获取请求参数，简单方便，该id参数必须传，否则报错（除非将 require 参数设为 false））
//        @DeleteMapping("/depts")
//        public Result delete(@RequestParam("id") int id) {
//            System.out.println("删除部门"+ id);
//            return Result.success();
//        }

        //方法三：省略@RequestParam注解（前端传递的请求参数名与服务端方法形参名一致）,推荐使用
        @LogOperation
        @DeleteMapping("/depts")
        public Result delete(Integer id) {
            //System.out.println("删除部门"+ id);
            log.info("删除部门：{}",id);
            deptService.deleteById(id);
            return Result.success();
        }

        @LogOperation
        @PostMapping("/depts")
        public Result add(@RequestBody Dept dept) {
            //System.out.println("添加部门"+ dept);
            log.info("添加部门：{}",dept);
            deptService.add(dept);
            return Result.success();
        }

        @GetMapping("/depts/{id}")
        public Result findById(@PathVariable Integer id) {
            //System.out.println("查询部门"+ id);
            log.info("查询部门：{}",id);
            Dept dept = deptService.findById(id);
            return Result.success(dept);
        }

        @LogOperation
        @PutMapping("/depts")
        public Result update(@RequestBody Dept dept) {
            //System.out.println("更新部门"+ dept);
            log.info("更新部门：{}",dept);
            deptService.update(dept);
            return Result.success();
        }
}
