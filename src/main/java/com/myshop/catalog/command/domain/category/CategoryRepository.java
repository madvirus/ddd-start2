package com.myshop.catalog.command.domain.category;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends Repository<Category, CategoryId> {
    Optional<Category> findById(CategoryId id);

    List<Category> findAll();
}
