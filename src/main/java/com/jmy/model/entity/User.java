package com.jmy.model.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String username;
    private String telephone;
    private String password;
    private String remark;
    private Integer deptId;
    private String operator;
    private Date operatorTime;
    private Integer valid;
    private String deptName;
    List<Permission> permissions;
}
