package com.jmy.dao;

import com.jmy.model.entity.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentMapper {
    void insert(Document document);
    List<Document> findAll(@Param("publisher")String publisher,
                           @Param("startIndex")Integer startIndex,
                           @Param("pageSize")Integer pageSize);
    Document findById(@Param("id")Integer id);
    Integer getRowCount(@Param("publisher")String publisher);
    void deleteById(@Param("documentId")Integer documentId);
    void update(Document document);
    void updateCount(@Param("documentId")Integer documentId);
    List<Document> getALL();
}
