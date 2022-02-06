package com.myshop.helper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbHelper {
    private JdbcTemplate jdbcTemplate;

    public DbHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void clear() {
        jdbcTemplate.update("truncate table member");
        jdbcTemplate.update("truncate table article");
        jdbcTemplate.update("truncate table article_content");
        jdbcTemplate.update("truncate table purchase_order");
        jdbcTemplate.update("truncate table order_line");
        jdbcTemplate.update("truncate table product");
        jdbcTemplate.update("truncate table image");
        jdbcTemplate.update("truncate table category");
        jdbcTemplate.update("truncate table product_category");
    }
}
