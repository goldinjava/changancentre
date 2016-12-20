package com.ctjsoft.xh.changancentre.facade;

import com.ctjsoft.xh.changancentre.service.FileUploadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class RestServieResourceFacade {

    private static final Logger LOG = Logger.getLogger(RestServieResourceFacade.class);

    @Autowired
    FileUploadService fileUploadService;

    public void upload(MultipartFile file) throws IOException {

        fileUploadService.upload(file);
    }
}
