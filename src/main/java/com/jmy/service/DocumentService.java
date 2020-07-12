package com.jmy.service;

import com.jmy.common.exception.CommonException;
import com.jmy.model.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DocumentService {
    void insert(Document document, MultipartFile multipartFile) throws CommonException;
    Map<String, Object> findAllByUser(Integer pageCurrent);
    Map<String, Object> findAllByPerm(Integer pageCurrent, Map<String, Object> searchParams);
    List<Document> findAllBySort(Integer documentType);
    Document findById(Integer id);
    void deleteById(Integer documentId) throws CommonException;
    void update(Document document,MultipartFile file) throws CommonException;

    void updateCount(Integer documentId);
}
