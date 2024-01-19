package com.stc.uploaddownload.files.repository;

import com.stc.uploaddownload.files.domain.DBPermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends JpaRepository<DBPermissions, Integer> {

    @Query("SELECT u FROM DBPermissions u WHERE u.userEmail = :userEmail")
    public DBPermissions getDBPermissionsByUserEmail(@Param("userEmail") String userEmail);

    DBPermissions findByUserEmail(String userEmail);
}
