package com.stc.uploaddownload.files.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBFile {
    @Id
    @SequenceGenerator(
            name = "files_id_sequence",
            sequenceName = "files_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "files_id_sequence"
    )
    private Integer id;

    @Lob
    private byte[] data;

    @OneToOne
    private DBItem item;

    public DBFile(byte[] bytes, DBItem dBItem) {
        this.data = bytes;
        this.item = dBItem;
    }
}
