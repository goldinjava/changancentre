package com.ctjsoft.xh.changancentre.service;

import com.ctjsoft.xh.changancentre.model.DocumentMetaData;
import com.ctjsoft.xh.changancentre.repository.MongoMetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2016/12/22.
 */

@Service
public class FileQueryService {
    @Autowired
    MongoMetaDataRepository mongoMetaDataRepository;



    private List<DocumentMetaData> constructList(Iterable<DocumentMetaData> persons) {
        List<DocumentMetaData> list = new ArrayList<DocumentMetaData>();
        for (DocumentMetaData person: persons) {
            list.add(person);
        }
        return list;
    }

    public List<DocumentMetaData> findByProjectName(String projectName) {
        return mongoMetaDataRepository.findByProjectName(projectName);
    }

    public List<DocumentMetaData> findAll() {
        return mongoMetaDataRepository.findAll();
    }

    public DocumentMetaData findVersoin(String projectName, String projectVersion) {
        return mongoMetaDataRepository.findOne(projectVersion);
    }
}
