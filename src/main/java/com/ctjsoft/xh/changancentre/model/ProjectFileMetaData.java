package com.ctjsoft.xh.changancentre.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tim on 2016/12/22.
 */
public class ProjectFileMetaData implements Serializable {

    String projectVersion;

    String fileName;

    Date modifyDate;

    public ProjectFileMetaData(String projectVersion, String fileName, Date modifyDate) {
        this.projectVersion = projectVersion;
        this.fileName = fileName;
        this.modifyDate = modifyDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }



    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
