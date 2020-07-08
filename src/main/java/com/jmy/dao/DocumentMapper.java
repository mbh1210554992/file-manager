package com.jmy.dao;

import com.jmy.model.Document;

import java.util.List;

public interface DocumentMapper {
    void insert(Document document);
    List<Document> findAll();
}
