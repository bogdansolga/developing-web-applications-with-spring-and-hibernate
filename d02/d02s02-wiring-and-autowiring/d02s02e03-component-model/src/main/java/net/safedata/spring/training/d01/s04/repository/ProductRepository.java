package net.safedata.spring.training.d01.s04.repository;

import net.safedata.spring.training.domain.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
@Repository
public class ProductRepository {

    private List<Product> products = Arrays.asList(
            new Product(1, "an iSomething", 50),
            new Product(1, "an iSomething else", 20)
    );

    public void displayProducts() {
        System.out.println("Displaying all the products:");
        for (Product product : products) {
            System.out.println("\t" + product.toString());
        }
    }
}
