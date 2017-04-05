package com.theappbusiness.marvel;

import android.content.Context;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by demir on 1.09.2016.
 */
public final class NetworkManager
{
    private static final String TAG = "NetworkManager";
    public static Retrofit retrofit;
    private static final String BASE_URL = "http://gateway.marvel.com";



    public static void initialize(Context context)
    {
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).build();



        // Create a very simple REST adapter which points the GitHub API.
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
