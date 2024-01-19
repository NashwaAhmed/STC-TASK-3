package com.stc.uploaddownload.files.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBPermissions {

    @Id
    @Column(name = "permission_id")
    @SequenceGenerator(
            name = "DBPermissions_id_sequence",
            sequenceName = "DBPermissions_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "DBPermissions_id_sequence"
    )
    private Integer id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String  userEmail;

    @NotNull
    @Column(nullable = false)
    private String password;

    private String permissionLevel;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id")
    )
    private Set<DBPermissionGroups> roleGroup = new HashSet<>();
}
