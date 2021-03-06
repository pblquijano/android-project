package com.pabloquijano.loginpokedex.utils;

import com.pabloquijano.loginpokedex.models.Pokemon_data;
import com.pabloquijano.loginpokedex.models.Pokemon_response;
import com.pabloquijano.loginpokedex.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;



public interface ApiInterface{
    @GET("/api/v2/pokemon/?limit=150")
    Call<Pokemon_response> getListPokemon();

    @POST("/signup")
    Call<User> signUp(@Body User user);


}

