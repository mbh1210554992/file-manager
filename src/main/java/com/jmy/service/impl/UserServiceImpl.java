package com.jmy.service.impl;

import com.jmy.common.exception.CommonException;

import com.jmy.dao.UserMapper;
import com.jmy.model.entity.User;
import com.jmy.model.PageObject;
import com.jmy.model.ResultCode;
import com.jmy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public Map<String, Object> findAllUer(String userName, Integer pageCurrent) {
        int pageSize=5;
        //1.2计算当前页开始查找的位置
        int startIndex=(pageCurrent-1)*pageSize;
        List<User> list = userMapper.findAllUer(userName, startIndex, pageSize);
        int rowCount = userMapper.getRowCount(userName);
        PageObject pageObject=new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setStartIndex(startIndex);
        //3.将数据封装到map(两个对象需要传回页面)
        Map<String,Object> map=
                new HashMap<String,Object>();
        //3.1封装当前页数据
        map.put("list", list);
        //3.2封装分页对象信息
        map.put("pageObject", pageObject);
        return map;

    }

    @Override
    public void validById(Integer userId, Integer valid) throws Exception {
        if(userId==null)
            throw new Exception("修改用户状态，用户id不能为空！");
        if(valid==null)
            throw new Exception("修改用户状态，valid值不能为空！");
        int i = userMapper.validById(userId,valid);
        if(i==-1)
            throw new Exception("切换用户启用禁用状态失败！");
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void intsertUser(User user) throws CommonException {
        User Operator = (User) SecurityUtils.getSubject().getPrincipal();
        User temp = userMapper.findByUsername(user.getUsername());
        if(temp!=null){
            throw new CommonException(ResultCode.USERNAME_ERROR);
        }
        String password = new Md5Hash(user.getPassword(),user.getUsername(),3).toString();
        user.setPassword(password);
        user.setOperator(Operator.getUsername());
        user.setOperatorTime(new Date());
        userMapper.intsertUser(user);
    }
}
