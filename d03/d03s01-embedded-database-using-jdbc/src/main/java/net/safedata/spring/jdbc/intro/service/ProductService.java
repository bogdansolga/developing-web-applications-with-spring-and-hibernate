package net.safedata.spring.jdbc.intro.service;

import net.safedata.spring.jdbc.intro.model.Product;
import net.safedata.spring.jdbc.intro.repository.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public int getCount() {
        return productRepository.getCount();
    }

    public Product getProduct(final int id) {
        return productRepository.getProduct(id);
    }
}
