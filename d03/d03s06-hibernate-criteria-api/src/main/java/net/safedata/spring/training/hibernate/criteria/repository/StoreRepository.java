package net.safedata.spring.training.hibernate.criteria.repository;

import net.safedata.spring.training.Store;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class StoreRepository {

    private final EntityManager entityManager;

    public StoreRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createStore(final Store store) {
        final EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        try {
            entityManager.persist(store);
            entityManager.flush();

            entityTransaction.commit();
        } catch (final Exception ex) {
            ex.printStackTrace();
            entityTransaction.rollback();
        }
    }

    public Store readStore(final int id) {
        return entityManager.find(Store.class, id);
    }
}
