package com.example.gadsleaderboard;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private List<Learner> mLearners = new ArrayList<>();
    private static DataManager ourInstance = null;

    public static DataManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataManager();
        }
        return ourInstance;
    }



}
