package com.myshop.board.domain;

import com.myshop.helper.DbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryIT {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DbHelper dbHelper;

    @BeforeEach
    void setUp() {
        dbHelper = new DbHelper(jdbcTemplate);
        dbHelper.clear();
    }

    @Test
    void save() {
        Article aritcle = new Article("title",
                new ArticleContent("content", "type")
        );
        articleRepository.save(aritcle);

        SqlRowSet rsArticle = jdbcTemplate.queryForRowSet(
                "select * from article where id = ?",
                aritcle.getId());
        assertThat(rsArticle.next()).isTrue();
        assertThat(rsArticle.getString("title")).isEqualTo("title");

        SqlRowSet rsContent = jdbcTemplate.queryForRowSet(
                "select * from article_content where id = ?",
                aritcle.getId());
        assertThat(rsContent.next()).isTrue();
        assertThat(rsContent.getString("content")).isEqualTo("content");
        assertThat(rsContent.getString("content_type")).isEqualTo("type");
    }

    @Test
    void findByIdNoData() {
        assertThat(articleRepository.findById(0L)).isEmpty();
    }

    @Test
    void findById() {
        jdbcTemplate.update("insert into article values (100, 'title')");
        jdbcTemplate.update("insert into article_content values (100, 'content', 'type')");

        Optional<Article> articleOpt = articleRepository.findById(100L);
        assertThat(articleOpt).isPresent();
        Article article = articleOpt.get();
        assertThat(article.getTitle()).isEqualTo("title");
        assertThat(article.getContent().getContent()).isEqualTo("content");
        assertThat(article.getContent().getContentType()).isEqualTo("type");
    }
}
