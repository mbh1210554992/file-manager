package com.jmy.service;

import com.jmy.model.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Create By Baihua on 2020/7/6.
 */
public interface DeptService {
    List<Map<String, Object>> findObjects();
    Dept findById(Integer id);
}
