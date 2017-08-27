package com.example.xianweili.googlebooklist;

import android.graphics.Bitmap;

/**
 * Created by xianwei li on 7/26/2017.
 */

public class Book {

    private String previewUrl;
    private Bitmap image;
    private String title;
    private String author;
    private String description;

    public Book(String previewUrl, Bitmap image, String title, String author, String description) {
        this.previewUrl = previewUrl;
        this.image = image;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

}
