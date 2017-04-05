package com.theappbusiness.marvel.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by demir on 5.09.2016.
 */
public interface IApi
{

    @GET("comics")
    Call<NetworkModels.GetComicsResponse> getComics(@Query("limit") Integer limit, @Query("offset") Integer offset);
}
