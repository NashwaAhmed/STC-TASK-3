package com.stc.uploaddownload.files.service.impl;

import com.stc.uploaddownload.files.domain.DBPermissions;
import com.stc.uploaddownload.files.repository.PermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PermissionsRepository permissionsRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        DBPermissions user = permissionsRepository.getDBPermissionsByUserEmail(s);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }
}
