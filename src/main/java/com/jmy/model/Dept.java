package com.jmy.model;

import lombok.*;

import java.util.Date;

/**
 * Create By Baihua on 2020/7/6.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dept {
    private Integer id;
    private String name;
    private Integer parentId;
    private String level;
    private String operator;
    private Date operatorTime;
    private String remark;
    private String parentName;
}
