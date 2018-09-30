package net.safedata.spring.training.d02.s05.config;

import net.safedata.spring.training.d02.s05.model.Product;
import net.safedata.spring.training.d02.s05.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * A simple {@link javax.sql.DataSource} configuration, which:
 * <ul>
 *     <li>configures the JPA repositories, using the {@link EnableJpaRepositories} annotation</li>
 *     <li>inserts a simple {@link Product} in the database, using the auto-configured {@link javax.sql.DataSource}</li>
 * </ul>
 */
@Configuration
@EnableJpaRepositories(basePackages = "net.safedata.spring.training.d02.s05.repository")
@EnableTransactionManagement
public class DataSourceConfig {

    private static final Random RANDOM = new Random(200);

    private final ProductService productService;

    @Autowired
    public DataSourceConfig(final ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        IntStream.range(0, 10)
                 .forEach(value -> {
                     final Product product = new Product();
                     product.setName("A default product with the ID " + RANDOM.nextInt(100));
                     productService.create(product);
                 });
    }
}
