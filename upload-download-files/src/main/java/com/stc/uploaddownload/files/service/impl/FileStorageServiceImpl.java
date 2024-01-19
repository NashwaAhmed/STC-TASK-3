package com.stc.uploaddownload.files.service.impl;

import com.stc.uploaddownload.files.domain.DBFile;
import com.stc.uploaddownload.files.domain.DBItem;
import com.stc.uploaddownload.files.domain.DBPermissionGroups;
import com.stc.uploaddownload.files.exception.FileNotFoundException;
import com.stc.uploaddownload.files.exception.FileStorageException;
import com.stc.uploaddownload.files.property.FileStorageProperties;
import com.stc.uploaddownload.files.repository.FilesRepository;
import com.stc.uploaddownload.files.repository.ItemRepository;
import com.stc.uploaddownload.files.repository.PermissionGroupsRepository;
import com.stc.uploaddownload.files.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    FilesRepository filesRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    PermissionGroupsRepository permissionGroupsRepository;

    @Transactional
    public DBFile store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String role= SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().toString();
        DBPermissionGroups dBPermissionGroups = permissionGroupsRepository.findByGroupName(role).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      //  DBPermissionGroups dBPermissionGroups= new DBPermissionGroups("ADMIN");
        DBItem dBItem = new DBItem(fileName,file.getContentType(),dBPermissionGroups);
        DBFile dBFile = new DBFile(file.getBytes(), dBItem);
       // permissionGroupsRepository.save(dBPermissionGroups);
        itemRepository.save(dBItem);
        return filesRepository.save(dBFile);
    }
    public DBFile getFile(Integer id) {
        String role= SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().toString();
        DBPermissionGroups dBPermissionGroups = permissionGroupsRepository.findByGroupName(role).get();
        log.info("dBPermissionGroups {}",dBPermissionGroups);
        return filesRepository.getByIdAndPermissionGroups(id, dBPermissionGroups);
    }


    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }


}
