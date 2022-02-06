package com.myshop.catalog.command.domain.product;

import com.myshop.common.model.Money;
import com.myshop.helper.DbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductRepositoryIT {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DbHelper dbHelper;

    @BeforeEach
    void setUp() {
        dbHelper.clear();
    }

    @Test
    void save() {
        List<Image> images = new ArrayList<>();
        images.add(new ExternalImage("https://extern.image.com/some-img.png"));
        images.add(new InternalImage("internal-img.png"));
        Product product = new Product(
                ProductId.of("PRD-01"),
                "제품-01",
                new Money(9000),
                "상세 내용",
                images);
        productRepository.save(product);

        SqlRowSet rsProd = jdbcTemplate.queryForRowSet("select * from product where product_id = ?", "PRD-01");
        assertThat(rsProd.next()).isTrue();
        assertThat(rsProd.getInt("price")).isEqualTo(9000);

        SqlRowSet rsImg = jdbcTemplate.queryForRowSet("select * from image where product_id = ? order by list_idx", "PRD-01");
        assertThat(rsImg.next()).isTrue();
        assertThat(rsImg.getString("image_type")).isEqualTo("EI");
    }

    @Transactional
    @Test
    void updateImages() {
        jdbcTemplate.update("insert into product values (?,?,?,?)", "PROD-02", "PRD 2", 10000, "상세");
        jdbcTemplate.update("insert into image values (?,?,?,?,?,?)",
                1, "PROD-02", 0, "EI", "http://images.img/img.01.png", LocalDateTime.now());
        jdbcTemplate.update("insert into image values (?,?,?,?,?,?)",
                2, "PROD-02", 1, "EI", "http://images.img/img.02.png", LocalDateTime.now());

        Product product = productRepository.findById(ProductId.of("PROD-02")).get();
        product.changeImages(List.of(
                new InternalImage("/path01.png"),
                new InternalImage("/path02.png")
        ));
        productRepository.flush();
    }
}