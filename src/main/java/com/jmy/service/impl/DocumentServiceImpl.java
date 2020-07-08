package com.jmy.service.impl;

import com.jmy.dao.DocumentMapper;
import com.jmy.model.Document;
import com.jmy.model.User;
import com.jmy.service.DocumentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public void insert(Document document) {
        User Operator = (User) SecurityUtils.getSubject().getPrincipal();
        document.setPublisher(Operator.getUsername());
        document.setPublisherDate(new Date());
        document.setPublisherDept(Operator.getDeptId());
        documentMapper.insert(document);
    }

    @Override
    public List<Document> findAll() {
        User operator = (User) SecurityUtils.getSubject().getPrincipal();
        List<Document> list =  documentMapper.findAll();
        List<Document> target = new ArrayList();
        for(Document document : list){
            if(operator.getDeptId().equals(document.getPublisherDept())){
                target.add(document);
            }
        }
        return target;
    }
}
