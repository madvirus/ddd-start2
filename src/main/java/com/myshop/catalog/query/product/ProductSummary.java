package com.myshop.catalog.query.product;

public class ProductSummary {
    private String id;
    private String name;
    private int price;
    private String image;

    public ProductSummary(String productId, String name, int price, String image) {
        this.id = productId;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
