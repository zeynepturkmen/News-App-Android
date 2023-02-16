package com.sabanciuniv.model;

import org.springframework.data.annotation.Id;

public class Comments {
	
    @Id private String id;
    private String nid;
    private String text, name;

    public Comments() {
    }

    public Comments(String news_id, String text, String name) {
        this.nid = news_id;
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
        return nid;
    }

    public void setNews_id(String news_id) {
        this.nid = news_id;
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
