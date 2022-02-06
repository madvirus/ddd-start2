package com.myshop.catalog.query.category;

import com.myshop.catalog.command.domain.category.CategoryId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryData {
    @EmbeddedId
    private CategoryId id;

    @Column(name = "name")
    private String name;

    protected CategoryData() {
    }

    public CategoryData(CategoryId id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
