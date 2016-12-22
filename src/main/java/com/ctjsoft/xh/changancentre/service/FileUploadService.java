package com.ctjsoft.xh.changancentre.service;

import com.ctjsoft.xh.changancentre.dao.DocumentFileSystemRepository;
import com.ctjsoft.xh.changancentre.model.Document;
import com.ctjsoft.xh.changancentre.model.DocumentMetaData;
import com.ctjsoft.xh.changancentre.model.ProjectFileMetaData;
import com.ctjsoft.xh.changancentre.repository.MongoMetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class FileUploadService {

    @Value("${file.repository}")
    private String fileStorageLocation;

    @Autowired
    DocumentFileSystemRepository documentFileSystemRepository;

    @Autowired
    MongoMetaDataRepository mongoMetaDataRepository;

    public void upload(String projectVersion,MultipartFile file) throws IOException {


        DocumentMetaData documentMetaData = mongoMetaDataRepository.findOne(projectVersion);
        if(documentMetaData==null){
            throw new RuntimeException(projectVersion + "版本信息未生成，请生成版本，再上传文件！");
        }
        String fileName = file.getOriginalFilename();
        byte[] content = file.getBytes();
        Document document = new Document(fileName, content);
        documentFileSystemRepository.add(documentMetaData.getStoreUrl(),document);

        ProjectFileMetaData fileMetaData = new ProjectFileMetaData(projectVersion,fileName,new Date());
        documentMetaData.putProjectFile(fileMetaData);
        mongoMetaDataRepository.save(documentMetaData);
    }

    public void createVersion(String projectName,String projectVersion,String projectType){
        DocumentMetaData documentMetaData = new DocumentMetaData(projectName,new Date(),fileStorageLocation,projectVersion,projectType);
        mongoMetaDataRepository.save(documentMetaData);
    }
}
