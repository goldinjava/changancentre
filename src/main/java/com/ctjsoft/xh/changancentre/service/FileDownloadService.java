package com.ctjsoft.xh.changancentre.service;

import com.ctjsoft.xh.changancentre.dao.DocumentFileSystemRepository;
import com.ctjsoft.xh.changancentre.model.DocumentMetaData;
import com.ctjsoft.xh.changancentre.repository.MongoMetaDataRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;


/**
 * Created by Tim on 2016/12/22.
 */

@Service
public class FileDownloadService {

    private static final Logger LOG = Logger.getLogger(FileDownloadService.class);

    @Value("${file.repository}")
    private String fileStorageLocation;

    @Autowired
    DocumentFileSystemRepository documentFileSystemRepository;

    @Autowired
    MongoMetaDataRepository mongoMetaDataRepository;


    public Resource findStreamByName(String projectName, String projectVersion, String fileName) {
        DocumentMetaData data = mongoMetaDataRepository.findOne(projectVersion);
        data.setProjectName(projectName);
        Resource resource = new FileSystemResource(data.getStoreUrl() + "/" +fileName);
        if(resource.exists() || resource.isReadable()) {
            return resource;
        }
        else{
            throw new RuntimeException("Could not find file: " + fileName);
        }

    }
}
