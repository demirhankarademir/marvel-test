package com.theappbusiness.marvel.network;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by demir on 1.09.2016.
 */
public final class NetworkManager
{
    private static final String TAG = "NetworkManager";
    public static Retrofit retrofit;
    private static final String BASE_URL = "https://gateway.marvel.com/v1/public/";
    public static final String PUBLIC_API_KEY = "03a38a14c654adeddcb10455dc3bba86";
    public static final String PRIVATE_API_KEY = "4c3a7243ef5d32b78e8a90338f094473837d5e57";



    public static void initialize(Context context)
    {
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new AuthInterceptor(PUBLIC_API_KEY, PRIVATE_API_KEY))
                .build();


//        List<Interceptor> interceptors = client.interceptors();
//        interceptors.add();


        // Create a very simple REST adapter which points the GitHub API.
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static void GetComics(int limit, int offset, Callback<NetworkModels.GetComicsResponse> callback)
    {



        // Create an instance of our GitHub API interface.
        IApi github = retrofit.create(IApi.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<NetworkModels.GetComicsResponse> call = github.getComics(limit, offset);

        call.enqueue(callback);
    }

}
