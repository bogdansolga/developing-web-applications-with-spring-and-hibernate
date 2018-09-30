package net.safedata.spring.jdbc.intro;

import net.safedata.spring.jdbc.intro.repository.ProductRepository;
import net.safedata.spring.jdbc.intro.service.ProductService;

public class JDBCMain {

    public static void main(final String[] args) {
        long now = System.currentTimeMillis();
        final ProductRepository productRepository = new ProductRepository();
        final ProductService productService = new ProductService(productRepository);
        System.out.println("The initial setup took " + (System.currentTimeMillis() - now) + " ms");

        System.out.println("There are " + productService.getCount() + " products in the database");

        final int id = 1;
        System.out.println("The product with the ID " + id + " is '" + productService.getProduct(id).getName() + "'");
    }
}
