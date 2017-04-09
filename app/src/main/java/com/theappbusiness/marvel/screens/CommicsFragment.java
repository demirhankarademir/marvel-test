package com.theappbusiness.marvel.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theappbusiness.marvel.R;
import com.theappbusiness.marvel.database.Comic;
import com.theappbusiness.marvel.database.DatabaseManager;

import java.util.List;

/**
 * Created by demir on 6.04.2017.
 */

public class CommicsFragment extends Fragment implements MainComicAdapter.OnItemClickListener
{
    MainComicAdapter comicAdapter;
    List<Comic> comicList;
    RecyclerView rvComics;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comics, container, false);

        rvComics = (RecyclerView) view.findViewById(R.id.rvComics);

        comicAdapter = new MainComicAdapter(getActivity(), this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvComics.setLayoutManager(mLayoutManager);
        rvComics.setItemAnimator(new DefaultItemAnimator());
        rvComics.setAdapter(comicAdapter);


        return view;
    }

    @Override
    public void onItemClick(Comic comic)
    {
        Intent i = new Intent(getActivity(), ComicDetailActivity.class);
        i.putExtra(ComicDetailActivity.INTENT_COMIC_ID, comic.getmId());
        startActivity(i);
    }

    public void onComicsUpdated()
    {
        comicList = DatabaseManager.getComics();
        comicAdapter.updateList(comicList);
    }
}
