package com.example.gadsleaderboard.models;

public class Learner {
    private String name;
    private String info;
    private String badgeUrl;



    public Learner(String name, String info, String badgeUrl) {
        this.name = name;
        this.info = info;
        this.badgeUrl = badgeUrl;

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

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

}
