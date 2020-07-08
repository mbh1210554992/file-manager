package com.jmy.service;

import com.jmy.model.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentService {
    void insert(Document document);
    List<Document> findAll();
    Document findById(Integer id);
}
