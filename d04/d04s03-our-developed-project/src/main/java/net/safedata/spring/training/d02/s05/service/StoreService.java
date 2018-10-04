package net.safedata.spring.training.d02.s05.service;

import net.safedata.spring.training.d02.s05.model.Address;
import net.safedata.spring.training.d02.s05.model.Manager;
import net.safedata.spring.training.d02.s05.model.Product;
import net.safedata.spring.training.d02.s05.model.Section;
import net.safedata.spring.training.d02.s05.model.Store;
import net.safedata.spring.training.d02.s05.repository.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class StoreService {

    // tip: choose the Logger class from the org.slf4j package
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);

    private final StoreRepository storeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @PostConstruct
    public void initialize() {
        final long now = System.currentTimeMillis();

        final Store store = new Store();
        store.setName("CCC");

        final Section section = new Section();
        section.setName("Ladies");

        final Set<Section> sections = new HashSet<>(1);
        sections.add(section);

        section.setStore(store);
        store.setSections(sections);

        final Address address = new Address();
        address.setCity("Iași");
        address.setNumber(999);
        address.setStreet("Over there");
        store.setAddress(address);

        final Product product = new Product();
        product.setName("Nice and sexy shoes");
        product.setPrice(- 200d);

        final Set<Product> products = new HashSet<>(1);
        products.add(product);

        section.setProducts(products);
        product.setSection(section);

        final Manager manager = new Manager();
        manager.setName("Gică");

        final Set<Manager> managers = new HashSet<>(1);
        managers.add(manager);

        store.setManagers(managers);

        storeRepository.save(store);

        LOGGER.info("Persisting the entities took {} ms", System.currentTimeMillis() - now);
    }

    public Iterable<Store> getAll() {
        try {
            Thread.sleep(new Random(200).nextInt(200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return storeRepository.findAll();
    }

    @SuppressWarnings("unused")
    public void entityManagerUsageExample() {
        @SuppressWarnings("unchecked")
        List<Product> theProducts = entityManager.createNamedQuery("Product.findByPrice")
                                                 .setParameter("price", 200)
                                                 .getResultList();
        for (Product it : theProducts) {
            System.out.println(it);
        }
    }
}
