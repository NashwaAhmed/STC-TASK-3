package com.stc.uploaddownload.files.service.impl;

import com.stc.uploaddownload.files.domain.DBPermissionGroups;
import com.stc.uploaddownload.files.repository.PermissionGroupsRepository;
import com.stc.uploaddownload.files.service.GroupAndRoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupAndRoleServiceImpl implements GroupAndRoleService {

    @Autowired
    PermissionGroupsRepository permissionGroupsRepository;

    @Override
    public DBPermissionGroups addGroup() {
        return null;
    }
}
