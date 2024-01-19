package com.stc.uploaddownload.files.repository;

import com.stc.uploaddownload.files.domain.DBFile;
import com.stc.uploaddownload.files.domain.DBItem;
import com.stc.uploaddownload.files.domain.DBPermissionGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FilesRepository extends JpaRepository<DBFile, Integer> {

    // @Query(value = "SELECT * FROM files as f join item as i on f.item_id = i.id WHERE f.id =?1 and i.permission_groups_id=?2", nativeQuery = true)
    @Query("SELECT f FROM DBFile f join DBItem i on f.item=i.id WHERE f.id = :id and i.permission=:dBPermissionGroups")
    DBFile getByIdAndPermissionGroups(@Param("id") Integer id, @Param("dBPermissionGroups") DBPermissionGroups dBPermissionGroups);
}
