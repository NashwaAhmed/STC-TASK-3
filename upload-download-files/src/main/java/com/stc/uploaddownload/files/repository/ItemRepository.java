package com.stc.uploaddownload.files.repository;

import com.stc.uploaddownload.files.domain.DBItem;
import com.stc.uploaddownload.files.domain.DBPermissionGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<DBItem, Integer> {

    DBItem findByPermission(DBPermissionGroups dBPermissionGroups);
}
