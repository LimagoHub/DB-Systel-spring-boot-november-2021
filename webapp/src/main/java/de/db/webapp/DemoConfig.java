package de.db.webapp;

import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class DemoConfig {

    private  String stadt;
    private  String land;
    private  String fluss;

    @Qualifier("stadtLandFluss")
    public List<String> getData() {
        return List.of(stadt, land, fluss);
    }
}
