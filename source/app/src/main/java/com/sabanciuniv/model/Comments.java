package com.sabanciuniv.model;

public class Comments {
    private int id, news_id;
    private String text, name;

    public Comments() {
    }

    public Comments(int id, int news_id, String text, String name) {
        this.id = id;
        this.news_id = news_id;
        this.text = text;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
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
