package net.safedata.spring.training.rest.support.repository;

import net.safedata.spring.training.domain.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class ProductRepository {

    private static final Object MUTEX = new Object();

    private static final Random RANDOM = new Random(100);

    // an in-memory list of products
    private List<Product> products = new ArrayList<>(1);

    @PostConstruct
    public void init() {
        products.add(getDefaultProduct());
        products.add(getDefaultProduct());
        products.add(getDefaultProduct());
    }

    public Product get(int id) {
        return products.get(id);
    }

    public List<Product> getAll() {
        return products;
    }

    public void create(final Product product) {
        products.add(product);
    }

    public void update(final int id, final Product product) {
        final int index = id < products.size() ? id : 0;
        synchronized (MUTEX) {
            products.set(index, product);
        }
    }

    public void delete(final int id) {
        products.remove(id < products.size() ? id : 0);
    }

    private Product getDefaultProduct() {
        return new Product(RANDOM.nextInt(50), "A fancy iSomething with the ID " + RANDOM.nextInt(), RANDOM.nextDouble());
    }
}
