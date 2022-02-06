package com.myshop.common.jpa;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RangeableExecutor<T> {
    List<T> getRange(Specification<T> spec, Rangeable rangeable);
}