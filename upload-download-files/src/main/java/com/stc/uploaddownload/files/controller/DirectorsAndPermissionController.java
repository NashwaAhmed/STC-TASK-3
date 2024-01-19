package com.stc.uploaddownload.files.controller;

import com.stc.uploaddownload.files.domain.DBFile;
import com.stc.uploaddownload.files.service.DirectorsAndPermissionService;
import com.stc.uploaddownload.files.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class DirectorsAndPermissionController {
    private static final Logger logger = LoggerFactory.getLogger(DirectorsAndPermissionController.class);
    @Autowired
    private DirectorsAndPermissionService directorsAndPermissionService;

    @GetMapping("/createSpace/{path:.+}")
    public ResponseEntity<String> createSpace(@PathVariable String path) {
        System.out.println("path  --==="+path);
        File file = new File(path);
        System.out.println("file  --==="+file.toPath());

        directorsAndPermissionService.createSpace(file);
        return ResponseEntity.ok()
                .body("succeed");
    }

}
