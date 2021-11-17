package de.db.webapp.services.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schwein{

    @Setter(AccessLevel.PRIVATE)
    private String id;
    @Setter(AccessLevel.PRIVATE)
    @Builder.Default
    private int version=0;
    @Builder.Default
    @Setter(AccessLevel.PRIVATE)
    private String name="nobody";
    @Builder.Default
    @Setter(AccessLevel.PRIVATE)
    private int gewicht=10;

    public void fuettern() {
        setGewicht(getGewicht() + 1);
    }
    public void umbennenen(String name) {
        if("Elsa".equals(name)) throw new IllegalArgumentException("Häh?");
        setName(name);
    }


}
