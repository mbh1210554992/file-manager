package com.jmy.service.impl;

import com.jmy.dao.FileTypeMapper;
import com.jmy.model.FileType;
import com.jmy.service.FileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileTypeServiceImpl implements FileTypeService {
    @Autowired
    private FileTypeMapper fileTypeMapper;

    @Override
    public FileType findById(Integer typeId) {
        return fileTypeMapper.findById(typeId);
    }
}
