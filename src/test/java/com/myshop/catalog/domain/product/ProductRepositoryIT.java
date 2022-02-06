package com.myshop.catalog.domain.product;

import com.myshop.catalog.command.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepositoryIT {
    @Autowired
    private ProductRepository productRepository;


}