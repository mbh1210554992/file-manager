package com.jmy.dao;

import com.jmy.model.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> findAllRole(@Param("startIndex")Integer startIndex,
                           @Param("pageSize")Integer pageSize);
    Integer getRowCount();
    void insertRolePerm(@Param("roleId") Integer roleId,
                        @Param("permId") Integer permId);

    void deleteRolePermByRoleId(@Param("roleId") Integer roleId);
    List<Integer> getPermIdByRoleId(@Param("roleId") Integer roleId);
    Role findRoleById(@Param("roleId") Integer roleId);
}
