package com.jmy.service.impl;

import com.jmy.dao.DeptMapper;
import com.jmy.model.Dept;
import com.jmy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create By Baihua on 2020/7/6.
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Map<String, Object>> findObjects() {
        return deptMapper.findObjects();
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }
}
