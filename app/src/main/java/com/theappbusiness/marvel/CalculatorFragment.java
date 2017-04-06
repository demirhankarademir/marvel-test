package com.theappbusiness.marvel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theappbusiness.marvel.database.Comic;
import com.theappbusiness.marvel.database.DatabaseManager;

import java.util.List;

/**
 * Created by demir on 6.04.2017.
 */

public class CalculatorFragment extends Fragment
{
    List<Comic> comicList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        return view;
    }

    public void onComicsUpdated()
    {
        comicList = DatabaseManager.getComics();
    }

}
