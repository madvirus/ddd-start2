package com.myshop.catalog.query.product;

import com.myshop.catalog.command.domain.category.CategoryId;
import com.myshop.catalog.command.domain.product.ProductId;
import com.myshop.helper.DbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductDataDaoIT {
    @Autowired
    private ProductDataDao productDataDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DbHelper dbHelper;

    @BeforeEach
    void setUp() {
        dbHelper.clear();
    }

    @Test
    void findByCategoryIds() {
        jdbcTemplate.update("insert into product values (?,?,?,?)", "PROD-01", "PRD 1", 10000, "상세");
        jdbcTemplate.update("insert into image values (?,?,?,?,?,?)",
                1, "PROD-02", 0, "EI", "http://images.img/img.01.png", LocalDateTime.now());
        jdbcTemplate.update("insert into product_category values (?, ?)", "PROD-01", 1);
        jdbcTemplate.update("insert into product_category values (?, ?)", "PROD-01", 2);

        jdbcTemplate.update("insert into product values (?,?,?,?)", "PROD-02", "PRD 2", 10000, "상세");
        jdbcTemplate.update("insert into product_category values (?, ?)", "PROD-02", 2);
        jdbcTemplate.update("insert into product_category values (?, ?)", "PROD-02", 3);

        jdbcTemplate.update("insert into product values (?,?,?,?)", "PROD-03", "PRD 3", 10000, "상세");
        jdbcTemplate.update("insert into product_category values (?, ?)", "PROD-03", 2);
        jdbcTemplate.update("insert into product_category values (?, ?)", "PROD-03", 3);

        jdbcTemplate.update("insert into product values (?,?,?,?)", "PROD-04", "PRD 4", 10000, "상세");
        jdbcTemplate.update("insert into product_category values (?, ?)", "PROD-04", 1);
        jdbcTemplate.update("insert into product_category values (?, ?)", "PROD-04", 3);

        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "id"));
        Page<ProductData> products = productDataDao.findByCategoryIdsContains(CategoryId.of(2L), pageRequest);
        assertThat(products.getNumberOfElements()).isEqualTo(2);
        assertThat(products.getContent()).hasSize(2);
        assertThat(products.getContent().get(0).getId()).isEqualTo(ProductId.of("PROD-03"));
        assertThat(products.getContent().get(1).getId()).isEqualTo(ProductId.of("PROD-02"));
    }
}