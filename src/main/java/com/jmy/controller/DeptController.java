package com.jmy.controller;

import com.jmy.common.exception.CommonException;
import com.jmy.model.entity.Dept;
import com.jmy.model.Result;
import com.jmy.model.ResultCode;
import com.jmy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Create By Baihua on 2020/7/6.
 */
@Controller
@RequestMapping("/dept/")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("list")
    public String listUI(){
        return "system/dept_list";
    }

    @RequestMapping("editUI")
    public String editUI(){
        return "system/dept_edit";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }


    @ResponseBody
    @RequestMapping("doFindObjects")
    public Result findAll(){
        List<Map<String, Object>> list = deptService.findObjects();
        return new Result(ResultCode.SUCCESS,deptService.findObjects());
    }
    @ResponseBody
    @RequestMapping("doFindById")
    public Result findById(@RequestParam("id") Integer id){
        return new Result(ResultCode.SUCCESS,deptService.findById(id));
    }

    @RequestMapping("doFindZtree")
    @ResponseBody
    public Result findZTree(){
        return new Result(ResultCode.SUCCESS, deptService.findZtreeNodes());
    }

    @RequestMapping("doDeleteById")
    @ResponseBody
    public Result deleteById(Integer deptId) throws CommonException {
        deptService.deleteById(deptId);
        return Result.SUCCESS();
    }

    @ResponseBody
    @RequestMapping("addDept")
    public Result addDept(@RequestBody Dept dept){
        deptService.insert(dept);
        return Result.SUCCESS();
    }

    @ResponseBody
    @RequestMapping("updateDept")
    public Result updateDept(@RequestBody Dept dept){
        deptService.update(dept);
        return Result.SUCCESS();
    }
}
