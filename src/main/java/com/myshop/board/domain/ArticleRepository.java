package com.myshop.board.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ArticleRepository extends Repository<Article, Long> {
    void save(Article article);

    Optional<Article> findById(Long id);
}
