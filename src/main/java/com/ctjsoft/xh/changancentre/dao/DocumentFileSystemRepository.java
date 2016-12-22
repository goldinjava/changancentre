package com.ctjsoft.xh.changancentre.dao;

import com.ctjsoft.xh.changancentre.model.Document;

import java.io.IOException;

public interface DocumentFileSystemRepository {

    public void add(String filePath,Document document) throws IOException;
}
