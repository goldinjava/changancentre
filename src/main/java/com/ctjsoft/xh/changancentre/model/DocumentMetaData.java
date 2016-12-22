package com.ctjsoft.xh.changancentre.model;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DocumentMetaData implements Serializable {

    static String PRODUCT_PROJECT = "product";
    static String PRIVATE_PROJECT = "private";


    //文件名称，包含路径
    private String projectName;

    //最后修改时间
    private Date lastModified;

    //存储根位置
    private String fileLocation;

    //工程版本
    @Id
    private String porjectVersion;

    //文件列表
    private List<ProjectFileMetaData> fileDataList;

    //工程属性：私有还是公有
    private String projectType;


    public DocumentMetaData() {
    }

    public DocumentMetaData(String projectName, Date lastModified, String fileLocation, String porjectVersion, String projectType) {
        this.projectName = projectName;
        this.lastModified = lastModified;
        this.fileLocation = fileLocation;
        this.porjectVersion = porjectVersion;
        this.projectType = projectType;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }


    public Date getLastModified() {
        return lastModified;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPorjectVersion() {
        return porjectVersion;
    }

    public void setPorjectVersion(String porjectVersion) {
        this.porjectVersion = porjectVersion;
    }

    public List<ProjectFileMetaData> getFileDataList() {
        return fileDataList;
    }

    public void setFileDataList(List<ProjectFileMetaData> fileDataList) {
        this.fileDataList = fileDataList;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public String getStoreUrl(){
        return this.getFileLocation() + "/" + this.getProjectName() + "/" + this.getProjectType() + "/" + this.getPorjectVersion();
    }

    public void putProjectFile(ProjectFileMetaData fileMetaData) {
        if(this.getFileDataList()==null){
            this.fileDataList = new ArrayList<>();
        }
        this.getFileDataList().add(fileMetaData);
    }
}
