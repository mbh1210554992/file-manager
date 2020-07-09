package com.jmy.model.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Permission {
    private Integer id;
    private String name;
    private String url;
}
