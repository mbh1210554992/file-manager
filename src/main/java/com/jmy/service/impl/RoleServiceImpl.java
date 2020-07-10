package com.jmy.service.impl;

import com.jmy.dao.RoleMapper;
import com.jmy.model.PageObject;
import com.jmy.model.entity.Role;
import com.jmy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String, Object> getAllRole(Integer pageCurrent) {
        int pageSize=5;
        //1.2计算当前页开始查找的位置
        int startIndex=(pageCurrent-1)*pageSize;
        List<Role> list = roleMapper.findAllRole(startIndex, pageSize);
        int rowCount = roleMapper.getRowCount();
        PageObject pageObject=new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setStartIndex(startIndex);
        Map<String,Object> map=
                new HashMap<String,Object>();
        //3.1封装当前页数据
        map.put("list", list);
        //3.2封装分页对象信息
        map.put("pageObject", pageObject);
        return map;
    }

    @Override
    @Transactional
    public void setRolePerm(Integer roleId, List<Integer> permIds) {
        roleMapper.deleteRolePermByRoleId(roleId);
        for(Integer permId : permIds){
            roleMapper.insertRolePerm(roleId, permId);
        }
    }

    @Override
    public List<Integer> getPermByRole(Integer roleId) {
        return roleMapper.getPermIdByRoleId(roleId);
    }
}
