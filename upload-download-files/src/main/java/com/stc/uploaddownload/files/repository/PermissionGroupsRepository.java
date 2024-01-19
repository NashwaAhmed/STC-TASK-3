package com.stc.uploaddownload.files.repository;

import com.stc.uploaddownload.files.domain.DBPermissionGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionGroupsRepository extends JpaRepository<DBPermissionGroups, Integer> {
    Optional<DBPermissionGroups> findByGroupName(String name);
}
