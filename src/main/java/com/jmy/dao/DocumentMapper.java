package com.jmy.dao;

import com.jmy.model.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentMapper {
    void insert(Document document);
    List<Document> findAll(@Param("publisher")String publisher);
    Document findById(@Param("id")Integer id);
}
