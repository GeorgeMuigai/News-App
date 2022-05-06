package com.gdev.news;

public class Articles {
    String title;
    String description;
    String url;
    String urlToImage;
    String content;

    public Articles(String title, String description, String url, String urlToImage, String content) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getContent() {
        return content;
    }
}
