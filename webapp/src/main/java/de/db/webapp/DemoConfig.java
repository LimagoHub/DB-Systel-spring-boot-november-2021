package de.db.webapp;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
@Configuration
@PropertySource("classpath:Demo.properties")
@ConfigurationProperties(prefix = "demo")
@Setter
public class DemoConfig {

    private  String stadt;
    private  String land;
    private  String fluss;

    @Bean
    @Qualifier("stadtLandFluss")
    public List<String> getData() {
        return List.of(stadt, land, fluss);
    }
}
