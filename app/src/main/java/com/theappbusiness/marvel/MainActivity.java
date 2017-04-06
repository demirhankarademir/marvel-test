package com.theappbusiness.marvel;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.theappbusiness.marvel.network.NetworkManager;
import com.theappbusiness.marvel.network.NetworkModels;
import com.theappbusiness.marvel.ui.SlidingTabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int NUMBER_OF_COMICS = 100;
    public static final int numberOfTabs = 2;
    private static final int offset = 0;

    private MainPageFPA pagerAdapter;
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.pager_title_strip);

        mViewPager.setOffscreenPageLimit(numberOfTabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        pagerAdapter = new MainPageFPA(this, getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        mSlidingTabLayout.setViewPager(mViewPager);

        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorPrimary);
            }

            @Override
            public int getDividerColor(int position) {
                return getResources().getColor(R.color.colorPrimary);
            }
        });

        getComics();
    }

    private void getComics()
    {
        //TODO we can add pagination here
        NetworkManager.GetComics(NUMBER_OF_COMICS, offset, new Callback<NetworkModels.GetComicsResponse>() {
            @Override
            public void onResponse(Call<NetworkModels.GetComicsResponse> call, Response<NetworkModels.GetComicsResponse> response) {
                if (response.code() == 200)
                {
                    NetworkModels.GetComicsResponse mResponse = response.body();
                }
            }

            @Override
            public void onFailure(Call<NetworkModels.GetComicsResponse> call, Throwable t) {

            }
        });
    }
}
