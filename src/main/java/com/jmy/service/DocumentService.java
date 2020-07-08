package com.jmy.service;

import com.jmy.model.Document;

import java.util.List;

public interface DocumentService {
    void insert(Document document);
    List<Document> findAll();
}
