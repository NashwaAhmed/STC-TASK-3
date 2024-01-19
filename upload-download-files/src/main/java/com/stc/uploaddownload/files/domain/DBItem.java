package com.stc.uploaddownload.files.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBItem {

    @Id
    @SequenceGenerator(
            name = "item_id_sequence",
            sequenceName = "item_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_id_sequence"
    )
    private Integer id;

    private String fileName;

    private String fileType;

    @ManyToOne
    private DBPermissionGroups permission;

    public DBItem(String fileName, String contentType, DBPermissionGroups dBPermissionGroups) {
        this.fileName = fileName;
        this.fileType = contentType;
        this.permission = dBPermissionGroups;
    }
}
