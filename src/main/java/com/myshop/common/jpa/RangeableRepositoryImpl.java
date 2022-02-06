package com.myshop.common.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class RangeableRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements RangeableRepository<T, ID> {

    public RangeableRepositoryImpl(
            JpaEntityInformation<T, ?> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public List<T> getRange(Specification<T> spec, Rangeable rangeable) {
        TypedQuery<T> query = getQuery(
                spec, getDomainClass(), rangeable.getSort());

        query.setFirstResult(rangeable.getStart());
        query.setMaxResults(rangeable.getLimit());

        return query.getResultList();
    }
}