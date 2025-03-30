package com.ab.newsapp;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class MainNews {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")  // Correct field name as per API response
    private int totalResults;  // Changed from String to int

    @SerializedName("articles")
    private ArrayList<Model> articles;

    public MainNews(String status, int totalResults, ArrayList<Model> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {  // Corrected method name
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Model> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Model> articles) {
        this.articles = articles;
    }
}
