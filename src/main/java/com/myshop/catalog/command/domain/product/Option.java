package com.myshop.catalog.command.domain.product;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Option {
    @Column(name = "option_value")
    private String value;
    @Column(name = "option_title")
    private String title;

    private Option() {
    }

    public Option(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
}
