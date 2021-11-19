package de.db.webapp.repositories.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_barkeepers")
public class BarKeeper {

    @Id
    private String id;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(nullable = false) // Foreign Key
    private Bar bar;
    private String keepername;
}
