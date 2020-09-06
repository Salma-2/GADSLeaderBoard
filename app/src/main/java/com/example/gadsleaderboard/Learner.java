package com.example.gadsleaderboard;

public class Learner {
    private String name;
    private String info;
    private String imageUrl;

    public Learner(String name, String info, String imageUrl) {
        this.name = name;
        this.info = info;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
