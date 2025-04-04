package com.ab.newsapp;

public class Model {
    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;
    public Model(String author,String title,String description,String url,String urlToImage,String publishedAt){
        this.author = author;
        this.title = title;
        this.description=description;
        this.url = url;
        this.urlToImage=urlToImage;
        this.publishedAt=publishedAt;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
