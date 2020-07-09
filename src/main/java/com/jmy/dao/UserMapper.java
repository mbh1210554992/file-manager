package com.jmy.dao;

import com.jmy.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User findByUsername(@Param("username")String username);
    List<User> findAllUer(@Param("username")String username,
                          @Param("startIndex")int startIndex,
                          @Param("pageSize")int pageSize);
    Integer getRowCount(@Param("username")String username);
    int validById(@Param("id")Integer id,@Param("valid")Integer valid);
    User findUserById(@Param("id")Integer id);
    void intsertUser(User user);
}
