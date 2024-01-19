package com.stc.uploaddownload.files.service;

import com.stc.uploaddownload.files.domain.DBFile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    public String storeFile(MultipartFile file);
    public Resource loadFileAsResource(String fileName);
    public DBFile store(MultipartFile file) throws IOException;
    public DBFile getFile(Integer id);
}
