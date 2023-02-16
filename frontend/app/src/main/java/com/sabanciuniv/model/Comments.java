package com.sabanciuniv.model;

public class Comments {
    private String news_id;
    private String id;
    private String text, name;

    public Comments() {
    }

    public Comments(String id, String news_id, String text, String name) {
        this.id = id;
        this.news_id = news_id;
        this.text = text;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
