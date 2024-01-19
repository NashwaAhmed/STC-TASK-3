package com.stc.uploaddownload.files.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
public class Response {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

}
