package com.jmy.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String username;
    private String telephone;
    private String password;
    private String remark;
    private Integer deptId;
    private String operator;
    private Date operatorTime;
    private Integer status;
    private String deptName;
}
