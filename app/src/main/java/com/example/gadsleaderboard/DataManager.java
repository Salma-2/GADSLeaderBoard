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

    public List<Learner> initializeLearner() {
        Learner learner1 = new Learner( "Salma", "250 learn hours, Egypt",  null );
        mLearners.add( learner1 );
        Learner learner2 = new Learner( "Salma", "250 learn hours, Egypt",  null );
        mLearners.add( learner2 );
        Learner learner3 = new Learner( "Salma", "250 learn hours, Egypt",  null );
        mLearners.add( learner3 );
        Learner learner4 = new Learner( "Salma", "250 learn hours, Egypt",  null );
        mLearners.add( learner4 );
        Learner learner5 = new Learner( "Salma", "250 learn hours, Egypt",  null );
        mLearners.add( learner5 );
        return mLearners;
    }


}
