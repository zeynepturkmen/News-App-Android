package com.sabanciuniv.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class News implements Serializable {
    private int id;
    private String category;
    private String title;
    private String text;
    private LocalDateTime date;
    private String imgPath;

    public News() {

    }

    public News(int id, String category, String title, String text, LocalDateTime date, String imgPath) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.text = text;
        this.date = date;
        this.imgPath = imgPath;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
