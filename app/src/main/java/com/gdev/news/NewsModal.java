package com.gdev.news;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsModal {
    String status;
    int totalResults;

    @SerializedName("articles")
    ArrayList<Articles> articles;

    public NewsModal(String status, int totalResults, ArrayList<Articles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }
}
