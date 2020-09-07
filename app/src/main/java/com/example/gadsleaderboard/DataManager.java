package com.example.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
