package com.gdev.news;

public class CategoriesModal {
    String category;
    String urlToImage;

    public CategoriesModal(String category, String urlToImage) {
        this.category = category;
        this.urlToImage = urlToImage;
    }

    public String getCategory() {
        return category;
    }

    public String getUrlToImage() {
        return urlToImage;
    }
}
