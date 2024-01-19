package com.stc.uploaddownload.files.service.impl;

import com.stc.uploaddownload.files.domain.DBPermissionGroups;
import com.stc.uploaddownload.files.domain.DBPermissions;
import com.stc.uploaddownload.files.model.UserRegisterDto;
import com.stc.uploaddownload.files.repository.PermissionsRepository;
import com.stc.uploaddownload.files.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private PermissionsRepository permissionsRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DBPermissions save(UserRegisterDto userRegDto) {

        DBPermissions user = new DBPermissions();
        user.setUserEmail(userRegDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        List<DBPermissionGroups> roles=new ArrayList<DBPermissionGroups>();
        Set<DBPermissionGroups> roleGroup = new HashSet<>();
        DBPermissionGroups role=new DBPermissionGroups();
        role.setGroupName(userRegDto.getGroupName());//"ROLE_USER"
        roleGroup.add(role);
        user.setRoleGroup(roleGroup);
        return permissionsRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        DBPermissions user = permissionsRepo.findByUserEmail(s);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoleGroup()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <DBPermissionGroups> roles) {
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getGroupName())).collect(Collectors.toList());
    }
}
