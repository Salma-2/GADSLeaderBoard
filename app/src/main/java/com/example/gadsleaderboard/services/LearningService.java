package com.example.gadsleaderboard.services;

import com.example.gadsleaderboard.models.Learner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningService {

    @GET("api/skilliq")
    Call<List<Learner>> getSkill();

    @GET("api/hours")
    Call<List<Learner>> getHours();
}
