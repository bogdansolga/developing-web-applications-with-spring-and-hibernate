package net.safedata.spring.training.d02.s05.repository;

import net.safedata.spring.training.d02.s05.model.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository("productRepositoryCustom")
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findByName(final String name) {
        final Session session = entityManager.unwrap(Session.class);
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);

        final Root<Product> from = criteriaQuery.from(Product.class);
        final Predicate predicate = builder.equal(from.get("name"), name);
        final CriteriaQuery<Product> query = criteriaQuery.select(from)
                                                          .where(predicate);

        final TypedQuery<Product> typedQuery = entityManager.createQuery(query);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }
}
