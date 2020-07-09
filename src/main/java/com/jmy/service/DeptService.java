package com.jmy.service;

import com.jmy.common.exception.CommonException;
import com.jmy.model.entity.Dept;

import java.util.List;
import java.util.Map;

/**
 * Create By Baihua on 2020/7/6.
 */
public interface DeptService {
    List<Map<String, Object>> findObjects();
    Dept findById(Integer id);
    List<Map<String,Object>> findZtreeNodes();
    void deleteById(Integer deptId) throws CommonException;
    void insert(Dept dept);
    void update(Dept dept);
}
