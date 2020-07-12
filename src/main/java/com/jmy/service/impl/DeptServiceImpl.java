package com.jmy.service.impl;

import com.jmy.common.exception.CommonException;
import com.jmy.dao.DeptMapper;
import com.jmy.model.entity.Dept;
import com.jmy.model.entity.User;
import com.jmy.model.ResultCode;
import com.jmy.service.DeptService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public List<Map<String, Object>> findZtreeNodes() {
        return deptMapper.findZtreeNodes();
    }

    @Override
    public void deleteById(Integer deptId) throws CommonException {
        int count = deptMapper.hasChild(deptId);
        if(count!=0){
            throw new CommonException(ResultCode.DEPT_CHIRD_ERROR);
        }
        deptMapper.deleteById(deptId);
    }

    @Override
    public void insert(Dept dept) {
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        dept.setOperator(operator.getUsername());
        dept.setOperatorTime(new Date());
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        dept.setOperator(operator.getUsername());
        dept.setOperatorTime(new Date());
        deptMapper.update(dept);
    }
}
