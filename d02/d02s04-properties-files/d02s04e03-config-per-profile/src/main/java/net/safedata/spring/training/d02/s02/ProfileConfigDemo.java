package net.safedata.spring.training.d02.s02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * A simple Spring Boot app which demos the usage of several Spring {@link org.springframework.context.annotation.Profile}s
 * with their corresponding configuration files
 *
 * @author bogdan.solga
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProfileConfigDemo {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(ProfileConfigDemo.class);

        springApplication.setAdditionalProfiles(Profiles.DEV);

        springApplication.run(args);
    }
}
