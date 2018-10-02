package net.safedata.spring.training.d02.s05.repository;

import net.safedata.spring.training.d02.s05.model.Product;

import java.util.Optional;

public interface ProductRepositoryCustom {

    Optional<Product> findByName(final String name);
}
