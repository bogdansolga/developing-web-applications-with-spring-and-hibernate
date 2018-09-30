package net.safedata.spring.training.d01.s04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * A simple Spring configuration, which wires the beans automatically via {@link ComponentScan}ing
 *
 * @author bogdan.solga
 */
@Configuration
@ComponentScan(basePackages = "net.safedata.spring.training.d01.s04") // --> implicit wiring
public class ComponentScanConfig {

    @Bean // --> explicit wiring
    public String anExplicitlyDefinedBean() {
        return "I am an explicitly defined bean";
    }
}
