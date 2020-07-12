package com.jmy.dao;

import com.jmy.model.entity.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DocumentMapper {
    void insert(Document document);
    List<Document> findAllByUser(@Param("publisher")String publisher,
                                 @Param("startIndex")Integer startIndex,
                                 @Param("pageSize")Integer pageSize);
    Document findById(@Param("id")Integer id);
    Integer findRowCountByUser(@Param("publisher")String publisher);
    void deleteById(@Param("documentId")Integer documentId);
    void update(Document document);
    void updateCount(@Param("documentId")Integer documentId);
    List<Document> findAllPublic(Map<String,Object> params);
    Integer findRowCountPublic(Map<String,Object> params);
    List<Document> findAllDocumentByPerm(Map<String,Object> params);

    Integer findRowCountByPerm(Map<String,Object> param);

    List<Document> findDocumentBySort(@Param("userName") String userName,
                                      @Param("deptId") Integer deptId,
                                      @Param("highDeptId") List<Integer> highDeptId,
                                      @Param("lowDeptId") List<Integer> lowDeptId,
                                      @Param("type") Integer type);
    List<Document> findAllPublicBySort(@Param("type") Integer type);
    Document findDocumentByNameAndUser(@Param("publisher")String publisher,
                                       @Param("name")String name);
}
