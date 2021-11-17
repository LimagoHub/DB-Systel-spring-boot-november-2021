package de.db.webapp.repositories.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_schweine")
public class SchweinEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;
    @Version
    @Column(nullable = false)
    private int version;
    @Column(length = 36, nullable = false)
    private String name;
    @Column(nullable = false)
    private int gewicht;
}
