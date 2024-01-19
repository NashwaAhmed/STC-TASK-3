package com.stc.uploaddownload.files.service;

import com.stc.uploaddownload.files.domain.DBPermissions;
import com.stc.uploaddownload.files.model.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    DBPermissions save(UserRegisterDto userRegDto);
}
