package com.ctjsoft.xh.changancentre.repository;

import com.ctjsoft.xh.changancentre.model.DocumentMetaData;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Tim on 2016/12/21.
 */
public interface MongoMetaDataRepository extends org.springframework.data.mongodb.repository.MongoRepository<DocumentMetaData, String> {

    List<DocumentMetaData> findByProjectName(@Param("projectName") String projectName);

}
