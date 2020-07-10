package com.jmy.dao;

import com.jmy.model.entity.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentMapper {
    void insert(Document document);
    List<Document> findAllByUser(@Param("publisher")String publisher,
                                 @Param("startIndex")Integer startIndex,
                                 @Param("pageSize")Integer pageSize);
    Document findById(@Param("id")Integer id);
    Integer getRowCountByUser(@Param("publisher")String publisher);
    void deleteById(@Param("documentId")Integer documentId);
    void update(Document document);
    void updateCount(@Param("documentId")Integer documentId);
    List<Document> findAll(@Param("startIndex")Integer startIndex,
                                 @Param("pageSize")Integer pageSize);
    Integer getRowCountByPublic();
    List<Document> getAllDocumentByPerm(@Param("userName") String userName,
                                        @Param("deptId") Integer deptId,
                                        @Param("highDeptId") List<Integer> highDeptId,
                                        @Param("lowDeptId") List<Integer> lowDeptId,
                                        @Param("startIndex")Integer startIndex,
                                        @Param("pageSize")Integer pageSize);

    Integer getRowCountByPerm(@Param("userName") String userName,
                                @Param("deptId") Integer deptId,
                                @Param("highDeptId") List<Integer> highDeptId,
                                @Param("lowDeptId") List<Integer> lowDeptId);
}
