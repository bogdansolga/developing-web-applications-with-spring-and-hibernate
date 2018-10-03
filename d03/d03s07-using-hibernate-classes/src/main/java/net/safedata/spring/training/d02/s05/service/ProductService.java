package net.safedata.spring.training.d02.s05.service;

import net.safedata.spring.training.d02.s05.model.Product;
import net.safedata.spring.training.d02.s05.repository.ProductRepository;
import net.safedata.spring.training.d02.s05.repository.ProductRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductRepositoryCustom productRepositoryCustom;

    @Autowired
    public ProductService(final ProductRepository productRepository,
                          final ProductRepositoryCustom productRepositoryCustom) {
        this.productRepository = productRepository;
        this.productRepositoryCustom = productRepositoryCustom;
    }

    @PostConstruct
    public void test() {
        final Optional<Product> product = productRepositoryCustom.findByName("A default product with the ID 67");
        product.ifPresent(it -> System.out.println(it.getId() + " - " + it.getName()));
    }

    public void create(final Product product) {
        productRepository.save(product);
    }

    public Product get(final int id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Nope"));
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public void update(final int id, final Product product) {
        final Product existingProduct = get(id);

        existingProduct.setName(product.getName());

        productRepository.save(product);
    }

    public void delete(final int id) {
        productRepository.deleteById(id);
    }
}
