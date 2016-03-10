package com.nikita.learnletters.network;

import com.nikita.learnletters.models.WordObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nikita on 10.03.16.
 */
public interface WordNetworkService {
    @GET("/api/v1/wordtasks")
    Call<ArrayList<WordObject>> getWords(@Query("meaningIds") String meaningIds, @Query("width") int width);
}
