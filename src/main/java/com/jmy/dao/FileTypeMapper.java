package com.jmy.dao;


import com.jmy.model.entity.FileType;
import org.apache.ibatis.annotations.Param;

public interface FileTypeMapper {
    FileType findById(@Param("typeId") Integer typeId);
}
