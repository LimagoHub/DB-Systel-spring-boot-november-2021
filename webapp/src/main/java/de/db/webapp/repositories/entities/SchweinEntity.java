package de.db.webapp.repositories.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Version;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// JPA Annotationen
public class SchweinEntity {

    private String id;
    @Version
    private int version;
    private String name;
    private int gewicht;
}
