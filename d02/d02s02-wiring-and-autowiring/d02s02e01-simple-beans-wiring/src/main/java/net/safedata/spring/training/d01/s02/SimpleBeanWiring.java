package net.safedata.spring.training.d01.s02;

import net.safedata.spring.training.d01.s02.config.DomainConfig;
import net.safedata.spring.training.d01.s02.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A simple demo for the usage of a Spring {@link org.springframework.context.annotation.Bean} which has a wired
 * collaborator
 *
 * @author bogdan.solga
 */
public class SimpleBeanWiring {

    private static final boolean USE_ANNOTATIONS_CONFIG = true;

    public static void main(String[] args) {
        final ApplicationContext applicationContext = buildApplicationContext();

        // retrieving and using the ProductService; it will have its dependency automatically wired by Spring
        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();

        //TODO implement the next example
        storeServiceWiring();
    }

    private static void storeServiceWiring() {
        //TODO:
        // create two beans - a StoreService and a StoreRepository
        // wire them using:
        //  - first: constructor setting
        //  - if (enoughTime): property setting
        // display a message from the store repository
    }

    private static ApplicationContext buildApplicationContext() {
        return USE_ANNOTATIONS_CONFIG ?
                new AnnotationConfigApplicationContext(DomainConfig.class) :
                new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
