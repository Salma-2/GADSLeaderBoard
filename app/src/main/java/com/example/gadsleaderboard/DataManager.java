package com.example.gadsleaderboard;

import com.example.gadsleaderboard.models.Learner;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static List<Learner> mLearners;
    public static List<Learner> mSkill;
    private static DataManager ourInstance = null;

    public static DataManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataManager();
            mLearners = new ArrayList<>();
            mSkill = new ArrayList<>();
        }
        return ourInstance;
    }
}
