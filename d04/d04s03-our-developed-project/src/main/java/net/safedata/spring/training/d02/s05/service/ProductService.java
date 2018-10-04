package net.safedata.spring.training.d02.s05.service;

import net.safedata.spring.training.d02.s05.model.Product;
import net.safedata.spring.training.d02.s05.model.Section;
import net.safedata.spring.training.d02.s05.repository.ProductRepository;
import net.safedata.spring.training.d02.s05.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SectionRepository sectionRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository, final SectionRepository sectionRepository) {
        this.productRepository = productRepository;
        this.sectionRepository = sectionRepository;
    }

    //@PostConstruct
    public void bootstrap() {
        final Product product = new Product();
        product.setName("Huawei P10");
        product.setPrice(250d);

        final Section section = new Section();
        section.setName("Phones");

        final Set<Product> products = new HashSet<>(1);
        products.add(product);

        // linking the section to the product
        section.setProducts(products);

        // linking the product to the section
        product.setSection(section);

        //create(product);
        createSection(section);

        /*
        final List<Product> savedProducts = jdbcTemplate.queryForList("SELECT * FROM Product", Product.class);
        savedProducts.forEach(it -> System.out.println(it));
        */
    }

    @Transactional
    public void createSection(Section section) {
        sectionRepository.save(section);
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
