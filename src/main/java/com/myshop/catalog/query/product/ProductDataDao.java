package com.myshop.catalog.query.product;

import com.myshop.catalog.command.domain.category.CategoryId;
import com.myshop.catalog.command.domain.product.ProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductDataDao extends Repository<ProductData, ProductId> {
    Optional<ProductData> findById(ProductId id);

    Page<ProductData> findByCategoryIdsContains(CategoryId id, Pageable pageable);
}
