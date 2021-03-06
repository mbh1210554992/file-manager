package com.jmy.service;

import com.jmy.common.exception.CommonException;
import com.jmy.model.entity.User;

import java.util.Map;

public interface UserService {
    Map<String, Object> findAllUer(String userName, Integer pageCurrent);
    void validById(Integer userId, Integer valid) throws Exception;
    User findUserById(Integer id);
    void intsertUser(User user) throws CommonException;
}
