package com.ctjsoft.xh.changancentre.dao;

import com.ctjsoft.xh.changancentre.model.Document;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class DocumentFileSystemRepositoryImp implements DocumentFileSystemRepository {

    private static final Logger LOG = Logger.getLogger(DocumentFileSystemRepositoryImp.class);

    @Value("${file.repository}")
    private String fileStorageLocation;

    @PostConstruct
    public void init() {
        createDirectory(fileStorageLocation);
    }

    @Override
    public void add(String filePath,Document document) throws IOException {

        if (org.springframework.util.StringUtils.isEmpty(document.getName())) {
            LOG.info("file name cant be null");
            throw new IOException();
        }

        createDirectory(filePath);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath, document.getName())));
        
        stream.write(document.getContent());
        stream.close();
    }

    private void createDirectory(String path) {
        File file = new File(path);
        file.mkdirs();
    }


//    public Path load(String filename) {
//        return rootLocation.resolve(filename);
//    }
//
//
//    public Resource loadAsResource(String filename) {
//        try {
//            Path file = load(filename);
//            Resource resource = new UrlResource(file.toUri());
//            if(resource.exists() || resource.isReadable()) {
//                return resource;
//            }
//            else {
//                throw new RuntimeException("Could not read file: " + filename);
//
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Could not read file: " + filename, e);
//        }
//    }


}
