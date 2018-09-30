package net.safedata.spring.training.d03s03.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "net.safedata.spring.training.d03s03.repository")
public class ProductServiceConfig {
}
