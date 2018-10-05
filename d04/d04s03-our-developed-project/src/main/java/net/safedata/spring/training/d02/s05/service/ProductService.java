package net.safedata.spring.training.d02.s05.service;

import net.safedata.spring.training.d02.s05.model.Product;
import net.safedata.spring.training.d02.s05.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public void create(final Product product) {
        productRepository.save(product);
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
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

    @SuppressWarnings("unused")
    public void paginationExample() {
        // 1st benefit - better method contract
        // 2nd benefit - chaining other processing methods on the result
        List<Product> pagedProducts = getPagedProducts();

        // 3rd benefit - separating the happy (processing) path from the unhappy path
        System.out.println(pagedProducts.size());
    }

    private List<Product> getPagedProducts() {
        final PageRequest pageRequest = PageRequest.of(0, 30, new Sort(Sort.Direction.DESC));
        return productRepository.findByPriceOrderByNameAsc(20, pageRequest);
    }
}
