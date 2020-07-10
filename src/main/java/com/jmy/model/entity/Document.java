package com.jmy.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Document {
    private Integer id;
    private String name;
    private String path;
    private String abstr;
    private Integer type;
    private String typeName;
    private Long size;
    private String publisher;
    private Integer publisherDept;
    private String DeptName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publisherDate;
    private Integer permId;
    private Integer viewCount;
    private Integer enableDown;
    private Integer rowCount;
}
