package com.ctjsoft.xh.changancentre.controller;

import com.ctjsoft.xh.changancentre.facade.RestServieResourceFacade;
import com.ctjsoft.xh.changancentre.model.DocumentMetaData;
import com.ctjsoft.xh.changancentre.model.HttpMessage;
import com.ctjsoft.xh.changancentre.model.ProjectFileMetaData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class RestApiEndPointController {

    private static final Logger LOG = Logger.getLogger(RestApiEndPointController.class);

    @Autowired
    RestServieResourceFacade restServieResourceFacade;

    @RequestMapping(value = "/projects/{projectName}/{projectVersion}/file", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<HttpMessage> upload(@RequestPart(value = "file", required = true) MultipartFile file,@PathVariable String projectVersion) throws IOException {

        if (!file.isEmpty()) {
            restServieResourceFacade.upload(projectVersion,file);
            HttpMessage message = new HttpMessage(projectVersion + " 版本上传成功！");
            return new ResponseEntity<HttpMessage>(message,new HttpHeaders(), HttpStatus.CREATED);
        }
        HttpMessage errormessage = new HttpMessage(projectVersion + " 版本上传失败！");
        return new ResponseEntity<HttpMessage>(errormessage,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/projects/{projectName}/{projectVersion:.+}",method = RequestMethod.POST)
    public ResponseEntity<HttpMessage> createVersion(@PathVariable String projectName,@PathVariable String projectVersion,@RequestParam(value = "type",defaultValue = "product") String projectType){
        restServieResourceFacade.createVersion(projectName,projectVersion,projectType);
        HttpMessage message = new HttpMessage(projectVersion + " 版本创建成功！");
        return new ResponseEntity<HttpMessage>(message,new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value="/projects/{projectName}",method = RequestMethod.GET)
    public List<DocumentMetaData> retrieve(@PathVariable String projectName) throws IOException{
        return restServieResourceFacade.findByProjectName(projectName);
    }

    @RequestMapping(value="/projects/{projectName}/{projectVersion:.+}",method = RequestMethod.GET)
    public DocumentMetaData retrieve(@PathVariable String projectName, @PathVariable String projectVersion) throws IOException{
        return restServieResourceFacade.findVersion(projectName,projectVersion);
    }

    @RequestMapping(value="/projects/{projectName}/{projectVersion}/{fileName:.+}",method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable String projectName,@PathVariable String projectVersion,@PathVariable String fileName) throws IOException{
        return restServieResourceFacade.download(projectName,projectVersion,fileName);
    }


    @RequestMapping(value="/projects",method = RequestMethod.GET)
    public List<DocumentMetaData> retrieveAll() throws IOException{
        return restServieResourceFacade.findAll();
    }
}
