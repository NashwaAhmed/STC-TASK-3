package com.stc.uploaddownload.files.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "permission_groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBPermissionGroups {

    @Id
    @Column(name = "groups_id")
    @SequenceGenerator(
            name = "permission_groups_id_sequence",
            sequenceName = "permission_groups_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "permission_groups_id_sequence"
    )
    private Integer id;

    @Column(unique = true, nullable = false)
    private String groupName;

    public DBPermissionGroups(String groupName)
    {
        this.groupName=groupName;
    }
}
