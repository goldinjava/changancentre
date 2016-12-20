package com.ctjsoft.xh.changancentre.service;

import com.ctjsoft.xh.changancentre.dao.DocumentFileSystemRepository;
import com.ctjsoft.xh.changancentre.model.Document;
import com.ctjsoft.xh.changancentre.model.DocumentMetaData;
import com.ctjsoft.xh.changancentre.repository.DocumentMetaDataRepository;
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
    DocumentMetaDataRepository documentRepository;

    public void upload(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        byte[] content = file.getBytes();
        Date currentDate = new Date();
        Document document = new Document(fileName, content);
        DocumentMetaData documentMetaData = new DocumentMetaData(fileName, fileStorageLocation, currentDate);
        documentFileSystemRepository.add(document);
        documentRepository.save(documentMetaData);
    }
}
