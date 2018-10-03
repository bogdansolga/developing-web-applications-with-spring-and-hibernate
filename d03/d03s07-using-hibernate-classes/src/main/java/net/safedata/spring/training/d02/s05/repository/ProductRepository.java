package net.safedata.spring.training.d02.s05.repository;

import net.safedata.spring.training.d02.s05.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A simple Spring Data {@link CrudRepository} for the {@link Product} entity
 *
 * @author bogdan.solga
 */
@Repository
@SuppressWarnings("unused")
public interface ProductRepository extends CrudRepository<Product, Integer>, ProductRepositoryCustom {

    Optional<Product> findById(int id);
}
