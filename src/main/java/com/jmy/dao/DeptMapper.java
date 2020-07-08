package com.jmy.dao;

import com.jmy.model.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Create By Baihua on 2020/7/6.
 */
public interface DeptMapper {
    List<Map<String, Object>> findObjects();
    Dept findById(@Param("id")Integer id);
    List<Map<String,Object>> findZtreeNodes();
    int hasChild(@Param("deptId") Integer deptId);
    void deleteById(@Param("deptId") Integer deptId);
    void insert(Dept dept);
    void update(Dept dept);
}
