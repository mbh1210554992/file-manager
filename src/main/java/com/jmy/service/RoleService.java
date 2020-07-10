package com.jmy.service;

import com.jmy.model.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    Map<String, Object> getAllRole(Integer pageCurrent);
    void setRolePerm(Integer roleId,List<Integer> permIds);
    List<Integer> getPermByRole(Integer roleId);
}
