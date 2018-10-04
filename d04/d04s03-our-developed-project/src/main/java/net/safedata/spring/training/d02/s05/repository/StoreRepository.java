package net.safedata.spring.training.d02.s05.repository;

import net.safedata.spring.training.d02.s05.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unused")
public interface StoreRepository extends CrudRepository<Store, Integer> {

    List<Store> findByName(@Param("name") final String name);

    void deleteAllByName(@Param("name") final String name);
}
