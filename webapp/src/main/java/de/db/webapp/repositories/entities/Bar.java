package de.db.webapp.repositories.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_bars")
@NamedQuery(name = "Bar.findAllWithKeeper", query = "select distinct b from Bar b inner join fetch b.keepers k ")
public class Bar {

    @Id
    private String id;
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "bar", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH, CascadeType.REMOVE})
    private List<BarKeeper> keepers = new ArrayList<>();

    public void addBarKeeper(BarKeeper keeper) {
        keeper.setBar(this);
        keepers.add(keeper);
    }



//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<Kontakt> kontakte = new ArrayList<>();
}
