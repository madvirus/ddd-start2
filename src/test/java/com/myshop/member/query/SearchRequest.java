package com.myshop.member.query;

public class SearchRequest {
    private boolean onlyNotBlocked;
    private String name;

    public boolean isOnlyNotBlocked() {
        return onlyNotBlocked;
    }

    public void setOnlyNotBlocked(boolean onlyNotBlocked) {
        this.onlyNotBlocked = onlyNotBlocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
