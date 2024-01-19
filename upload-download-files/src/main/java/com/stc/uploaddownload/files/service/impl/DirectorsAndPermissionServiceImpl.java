package com.stc.uploaddownload.files.service.impl;

import com.stc.uploaddownload.files.service.DirectorsAndPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DirectorsAndPermissionServiceImpl implements DirectorsAndPermissionService {

    public void createSpace(File file)
    {
        File file1 = new File("/var/www/newDirectory/");
        file1.mkdirs();
        file1.setReadable(true,true);
        file1.setReadable(false); //deny for all
        file1.setWritable(false);
        file1.setExecutable(false);
        file1.setReadable(true, true); //allow for owner
        file1.setWritable(true, true);
        file1.setWritable(true, true); //optional
        String directoryPath = "D:/SampleDirectory";
        String str ="ADMIN";
        log.info("permission {}",str);
        String toOctal = str.chars().boxed().map(Integer::toOctalString)
                .collect(Collectors.joining(" "));
        System.out.println(toOctal);
        // must be 9 digit
        String perm = "rwxrwx---";// in octal = 770
        try {
          /*  Set<PosixFilePermission> permissions =
                    PosixFilePermissions.fromString("r--------");
            Files.setPosixFilePermissions(file.toPath(), permissions);*/
            Set<PosixFilePermission> ownerWritable = PosixFilePermissions.fromString("rw-r--r--");
            FileAttribute<?> permissions = PosixFilePermissions.asFileAttribute(ownerWritable);
            Files.createFile(file.toPath(), permissions);
        } catch (IOException e) {
            // logger.warning("Can't set permission '" + perm + "' to " +   dir.getPath());
            e.printStackTrace();
        }

    }
}
