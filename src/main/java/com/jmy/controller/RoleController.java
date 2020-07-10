package com.jmy.controller;

import com.jmy.dao.RoleMapper;
import com.jmy.model.entity.Result;
import com.jmy.model.entity.ResultCode;
import com.jmy.model.entity.Role;
import com.jmy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/role/")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @RequestMapping("listUI")
    public String listUI(){
        return "system/role_list";
    }

    @ResponseBody
    @RequestMapping("doGetRoles")
    public Result getAllRoles(Integer pageCurrent){
        return new Result(ResultCode.SUCCESS,roleService.getAllRole(pageCurrent));
    }

    @ResponseBody
    @RequestMapping("setRolePerm")
    public Result setRolePerm(Integer id, String permIds){
        if("".equals(permIds)){
            roleMapper.deleteRolePermByRoleId(id);
            return Result.SUCCESS();
        }
        List<String> list = Arrays.asList(permIds.split(","));
        List<Integer> ids = new ArrayList<>();
        for(String temp : list){
            Integer permId = Integer.parseInt(temp);
            ids.add(permId);
        }

        roleService.setRolePerm(id, ids);
        return Result.SUCCESS();
    }

    @ResponseBody
    @RequestMapping("getPerm")
    public Result getPermByRole(Integer roleId){
        return new Result(ResultCode.SUCCESS,roleService.getPermByRole(roleId));
    }
}
