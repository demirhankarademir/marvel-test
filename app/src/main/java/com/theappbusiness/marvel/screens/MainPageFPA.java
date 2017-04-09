package com.theappbusiness.marvel.screens;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.theappbusiness.marvel.R;

/**
 * Created by demir on 10.01.2017.
 */

public class MainPageFPA extends FragmentStatePagerAdapter {
    Context context;
    CommicsFragment commicsFragment;
    CalculatorFragment calculatorFragment;

    FragmentManager fm;

    public MainPageFPA(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;

        this.fm = fm;
        commicsFragment = new CommicsFragment();
        calculatorFragment = new CalculatorFragment();
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0)
        {
            return commicsFragment;
        }
        else
        {
            return calculatorFragment;
        }
    }

    @Override
    public int getCount() {
        return MainActivity.numberOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.comics);
            case 1:
                return context.getResources().getString(R.string.calculator);
            default:
                return null;
        }
    }

    public void showComics()
    {
        commicsFragment.onComicsUpdated();
        calculatorFragment.showCommics();
    }


}