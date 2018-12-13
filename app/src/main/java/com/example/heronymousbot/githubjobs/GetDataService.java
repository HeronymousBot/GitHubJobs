package com.example.heronymousbot.githubjobs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("positions.json")
    Call<List<Job>> getAllJobs(@Query("description") String descriptionQuery, @Query("location") String locationQuery);
}
