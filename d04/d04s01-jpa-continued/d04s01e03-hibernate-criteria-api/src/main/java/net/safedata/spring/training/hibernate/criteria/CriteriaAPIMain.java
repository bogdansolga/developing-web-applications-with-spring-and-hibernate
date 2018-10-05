package net.safedata.spring.training.hibernate.criteria;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.spring.training.jpa.model.Store;
import net.safedata.spring.training.jpa.model.Section;
import net.safedata.spring.training.hibernate.criteria.repository.ProductRepository;
import net.safedata.spring.training.hibernate.criteria.service.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;

public class CriteriaAPIMain {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private static ProductRepository productRepository;
    private static ProductService productService;

    static {
        // disable Hibernate's logging messages
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-intro");
        entityManager = entityManagerFactory.createEntityManager();

        H2ServerInit.initializeDatabaseServer();
    }

    public static void main(final String[] args) {
        productRepository = new ProductRepository(entityManager);
        productService = new ProductService(productRepository);

        productCRUD();
        //storeCRUD();

        // criteriaAPISample();

        closeEntityManagerObjects();
    }

    private static void productCRUD() {
        final Product product = new Product();
        product.setName("Coffee");
        productService.saveProduct(product);

        final Product tea = new Product();
        tea.setName("Tea");
        productService.saveProduct(tea);

        final Product anotherCupOfCoffee = new Product();
        anotherCupOfCoffee.setName("Coffee");
        productService.saveProduct(anotherCupOfCoffee);

        // read product, by ID
        //final Product savedProduct = productService.getProduct(1);
        //System.out.println("The saved product is '" + savedProduct.getProductName() + "'");

        // update product
        //product.setProductName("A very good coffee");
        //productService.updateProduct(product);

        // final Product updatedProduct = productService.getProduct(1);
        //System.out.println("The updated product name is '" + updatedProduct.getProductName() + "'");

        // delete the previously saved product
        // productRepository.deleteProduct(updatedProduct);

        // System.out.println("There is no product with the ID 1 - " + (productRepository.getProduct(1) == null));
    }

    private static void criteriaAPISample() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        final Root<Product> from = criteriaQuery.from(Product.class);
        final CriteriaQuery<Product> select = criteriaQuery.select(from);
        final TypedQuery<Product> typedQuery = entityManager.createQuery(select);

        final List<Product> products = typedQuery.getResultList();
        products.forEach(product -> System.out.println(product.getName()));
    }

    private static void storeCRUD() {
        final Store store = new Store();
        store.setName("Zara");

        final Product product = new Product();
        product.setName("A fancy t-shirt");

        final Set<Product> products = new HashSet<>(1);
        products.add(product);

        final Set<Section> sections = new HashSet<>(1);
        final Section section = new Section();
        section.setProducts(products);

        section.setStore(store);
        store.setSections(sections);

        entityManager.persist(store);
    }

    private static void closeEntityManagerObjects() {
        Optional.ofNullable(entityManager).ifPresent(EntityManager::close);
        Optional.ofNullable(entityManagerFactory).ifPresent(EntityManagerFactory::close);
    }
}
