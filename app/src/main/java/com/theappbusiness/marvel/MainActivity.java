package com.theappbusiness.marvel;

import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.theappbusiness.marvel.database.DatabaseManager;
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
    private ProgressDialog pDialog;

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
        pDialog = ProgressDialog.show(this, getText(R.string.app_name), getText(R.string.please_wait), true);

        //TODO we can add pagination here
        NetworkManager.GetComics(NUMBER_OF_COMICS, offset, new Callback<NetworkModels.GetComicsResponse>() {
            @Override
            public void onResponse(Call<NetworkModels.GetComicsResponse> call, Response<NetworkModels.GetComicsResponse> response) {
                if (response.code() == 200)
                {
                    NetworkModels.GetComicsResponse mResponse = response.body();
                    DatabaseManager.updateComics(mResponse.data.results);
                    pagerAdapter.showComics();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<NetworkModels.GetComicsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, getText(R.string.connection_problem), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
                pagerAdapter.showComics();
            }
        });
    }
}
