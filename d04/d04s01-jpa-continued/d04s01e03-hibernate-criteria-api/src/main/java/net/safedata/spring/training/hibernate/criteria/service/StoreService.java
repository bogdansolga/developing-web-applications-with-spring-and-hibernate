package net.safedata.spring.training.hibernate.criteria.service;

import net.safedata.spring.training.jpa.model.Store;
import net.safedata.spring.training.hibernate.criteria.repository.StoreRepository;

public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(final StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public void createStore(final Store store) {
        // might have a very complex processing before saving the store
        storeRepository.createStore(store);
    }

    public Store readStore(final int id) {
        return storeRepository.readStore(id);
    }
}
