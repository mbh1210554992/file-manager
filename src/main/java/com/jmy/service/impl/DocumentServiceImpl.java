package com.jmy.service.impl;

import com.jmy.dao.DocumentMapper;
import com.jmy.model.Dept;
import com.jmy.model.Document;
import com.jmy.model.User;
import com.jmy.service.DeptService;
import com.jmy.service.DocumentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private DeptService deptService;

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
            else if(document.getPermId() == 2){
                Dept dept = deptService.findById(2);
                Integer parentId = dept.getParentId();
                List<Integer> deptIds = new ArrayList<>();
                deptIds.add(dept.getId());
                while(dept != null){
                    dept = deptService.findById(parentId);
                    parentId = dept.getId();
                    deptIds.add(dept.getId());
                }
            }
        }
        return target;
    }

    @Override
    public Document findById(Integer id) {
        return documentMapper.findById(id);
    }
}
