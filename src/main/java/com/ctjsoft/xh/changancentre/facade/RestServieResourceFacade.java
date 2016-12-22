package com.ctjsoft.xh.changancentre.facade;

import com.ctjsoft.xh.changancentre.model.DocumentMetaData;
import com.ctjsoft.xh.changancentre.model.ProjectFileMetaData;
import com.ctjsoft.xh.changancentre.service.FileDownloadService;
import com.ctjsoft.xh.changancentre.service.FileQueryService;
import com.ctjsoft.xh.changancentre.service.FileUploadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceTransformer;

import java.io.IOException;
import java.util.List;

@Component
public class RestServieResourceFacade {

    private static final Logger LOG = Logger.getLogger(RestServieResourceFacade.class);

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    FileDownloadService fileDownloadService;

    @Autowired
    FileQueryService fileQueryRepository;

    public void upload(String sVersion,MultipartFile file) throws IOException {
        fileUploadService.upload(sVersion,file);
    }

    public ResponseEntity<Resource> download(String projectName, String projectVersion, String fileName){
        Resource file = fileDownloadService.findStreamByName(projectName,projectVersion,fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+fileName+"\"").body(file);
    }

    public void createVersion(String projectName,String projectVersion,String projectType){
        fileUploadService.createVersion(projectName,projectVersion,projectType);
    }

    public List<DocumentMetaData> findByProjectName(String projectName) {
        return fileQueryRepository.findByProjectName(projectName);
    }

    public DocumentMetaData findVersion(String projectName, String projectVersion) {
        return fileQueryRepository.findVersoin(projectName,projectVersion);
    }

    public List<DocumentMetaData> findAll() {
        return fileQueryRepository.findAll();
    }
}
