package com.ctjsoft.xh.changancentre.model;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import org.springframework.data.annotation.Id;

import java.beans.Transient;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

//@Entiy
public class DocumentMetaData implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String name;

    private Date lastModified;

    private String fileLocation;

    public DocumentMetaData() {
    }

    public DocumentMetaData(String name, String fileLocation, Date lastModified) {
        this.setId(System.currentTimeMillis());
        this.setLastModified(lastModified);
        this.setName(name);
        this.setFileLocation(fileLocation);

    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id){ this.id = id;}

    public Date getLastModified() {
        return lastModified;
    }

    public String getName() {
        return name;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public Long getId() {
        return id;
    }
}
