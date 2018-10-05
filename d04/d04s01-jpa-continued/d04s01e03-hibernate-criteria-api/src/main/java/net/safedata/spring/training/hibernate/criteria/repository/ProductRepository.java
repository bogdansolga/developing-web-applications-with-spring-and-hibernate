package net.safedata.spring.training.hibernate.criteria.repository;

import net.safedata.spring.training.jpa.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ProductRepository {

    private final EntityManager entityManager;

    public ProductRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product getProduct(final int id) {
        return entityManager.find(Product.class, id);
    }

    public void saveProduct(final Product product) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        try {
            entityManager.persist(product);
            entityManager.flush();

            transaction.commit();
        } catch (final Exception ex) {
            transaction.rollback();
        }
    }

    public void updateProduct(final Product product) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        try {
            entityManager.merge(product);
            entityManager.flush();

            transaction.commit();
        } catch (final Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void deleteProduct(final Product product) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        try {
            entityManager.remove(product);
            entityManager.flush();

            transaction.commit();
        } catch (final Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
