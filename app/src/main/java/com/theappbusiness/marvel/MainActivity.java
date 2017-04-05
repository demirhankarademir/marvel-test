package com.theappbusiness.marvel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.theappbusiness.marvel.network.NetworkManager;
import com.theappbusiness.marvel.network.NetworkModels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int NUMBER_OF_COMICS = 10;
    private int offset = 0;

    private RecyclerView rvComic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvComic = (RecyclerView) findViewById(R.id.rvComic);

        getComics();
    }

    private void getComics()
    {
        NetworkManager.GetComics(NUMBER_OF_COMICS, offset, new Callback<NetworkModels.GetComicsResponse>() {
            @Override
            public void onResponse(Call<NetworkModels.GetComicsResponse> call, Response<NetworkModels.GetComicsResponse> response) {
                if (response.code() == 200)
                {
                    NetworkModels.GetComicsResponse mResponse = response.body();
                    mResponse.toString();
                }
            }

            @Override
            public void onFailure(Call<NetworkModels.GetComicsResponse> call, Throwable t) {

            }
        });
    }
}
