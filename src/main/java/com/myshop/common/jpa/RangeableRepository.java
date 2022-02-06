package com.myshop.common.jpa;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface RangeableRepository<T, ID extends Serializable>
        extends Repository<T, ID>, RangeableExecutor<T> {
}