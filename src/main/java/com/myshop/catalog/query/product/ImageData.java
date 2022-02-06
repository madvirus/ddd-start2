package com.myshop.catalog.query.product;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class ImageData {
    @Column(name = "image_path")
    private String path;

    private LocalDateTime uploadTime;

}
