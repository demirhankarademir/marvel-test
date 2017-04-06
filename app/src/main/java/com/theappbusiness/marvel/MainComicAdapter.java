package com.theappbusiness.marvel;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.theappbusiness.marvel.database.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demir on 30.11.2016.
 */

public class MainComicAdapter extends RecyclerView.Adapter<MainComicAdapter.ViewHolder> {


    private List<Comic> mList = new ArrayList<>();
    private Activity activity;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView ivImage;
        TextView tvTitle;
        public ViewHolder(View v) {
            super(v);
            ivImage = (ImageView) v.findViewById(R.id.ivImage);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        }
    }

    public MainComicAdapter(Activity activity) {
        this.activity = activity;
    }

    public void updateList(List<Comic> newList) {

        mList.clear();

        mList.addAll(newList);
        notifyDataSetChanged();
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_main_comic, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Comic comic = mList.get(position);

        if (comic.getThumbnail() != null && !comic.getThumbnail().isEmpty())
        {
            Picasso.with(activity).load(comic.getThumbnail()).into(holder.ivImage);
        }
        holder.tvTitle.setText(comic.getTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mList.size();
    }
}