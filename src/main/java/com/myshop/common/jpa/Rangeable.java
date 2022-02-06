package com.myshop.common.jpa;

import org.springframework.data.domain.Sort;

public class Rangeable {
    private int start;
    private int limit;
    private Sort sort;

    public Rangeable(int start, int limit, Sort sort) {
        this.start = start;
        this.limit = limit;
        this.sort = sort;
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }

    public Sort getSort() {
        return sort;
    }

    public static Rangeable of(int start, int limit) {
        return new Rangeable(start, limit, Sort.unsorted());
    }
}