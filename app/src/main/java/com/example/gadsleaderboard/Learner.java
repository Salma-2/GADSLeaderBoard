package com.example.gadsleaderboard;

public class Learner {
    private String name;
    private String info;

    public Learner(String name, String info) {
        this.name = name;
        this.info = info;

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

}
