package com.ctjsoft.xh.changancentre.repository;

import com.ctjsoft.xh.changancentre.model.DocumentMetaData;

/**
 * Created by Tim on 2016/12/21.
 */
public interface MongoMetaDataRepository extends org.springframework.data.mongodb.repository.MongoRepository<DocumentMetaData, Long> {

}
