package com.example.songdemo.model;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singer;
    private String album;
    private String category;
    private Boolean isFavor;

    public Song(int id, String title, String singer, String album, String category, Boolean isFavor) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.album = album;
        this.category = category;
        this.isFavor = isFavor;
    }

    public Song(String title, String singer, String album, String category, Boolean isFavor) {
        this.title = title;
        this.singer = singer;
        this.album = album;
        this.category = category;
        this.isFavor = isFavor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getFavor() {
        return isFavor;
    }

    public void setFavor(Boolean favor) {
        isFavor = favor;
    }
}
