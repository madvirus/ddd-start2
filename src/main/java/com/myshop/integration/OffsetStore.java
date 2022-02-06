package com.myshop.integration;

public interface OffsetStore {
    long get();
    void update(long nextOffset);
}
