package com.jmy.service;

import com.jmy.common.exception.CommonException;
import com.jmy.model.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface DocumentService {
    void insert(Document document, MultipartFile multipartFile);
    Map<String, Object> findAllByUser(Integer pageCurrent);
    Map<String, Object> findAllByPerm(Integer pageCurrent);
    Document findById(Integer id);
    void deleteById(Integer documentId) throws CommonException;
    void update(Document document,MultipartFile file);

    void updateCount(Integer documentId);
}
