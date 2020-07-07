package com.jmy.service;

import com.jmy.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, Object> findAllUer(String userName, Integer pageCurrent);
    void validById(Integer userId, Integer valid);
}
