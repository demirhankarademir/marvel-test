package com.theappbusiness.marvel;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.theappbusiness.marvel.database.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demir on 30.11.2016.
 */

public class CalculatorComicAdapter extends RecyclerView.Adapter<CalculatorComicAdapter.ViewHolder> {
    private List<Comic> mList = new ArrayList<>();
    private Activity activity;
    private OnItemClickListener onItemClickListener;


    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView ivImage;
        TextView tvTitle, tvPrice, tvNumberOfPages;
        LinearLayout llContainer;

        public ViewHolder(View v) {
            super(v);
            ivImage = (ImageView) v.findViewById(R.id.ivImage);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            llContainer = (LinearLayout) v.findViewById(R.id.llContainer);
            tvPrice = (TextView) v.findViewById(R.id.tvPrice);
            tvNumberOfPages = (TextView) v.findViewById(R.id.tvNumberOfPages);
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Comic comic);
    }



    public CalculatorComicAdapter(Activity activity, OnItemClickListener onItemClickListener)
    {
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
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
                .inflate(R.layout.row_calculator_comic, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Comic comic = mList.get(position);

        if (comic.getImageThumbnail() != null && !comic.getImageThumbnail().isEmpty())
        {
            Picasso.with(activity).load(comic.getImageThumbnail()).into(holder.ivImage);
        }
        holder.tvTitle.setText(comic.getTitle());
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(comic);
            }
        });
        holder.tvPrice.setText(comic.getPrice()+"");
        holder.tvNumberOfPages.setText(comic.getPageCount()+"");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mList.size();
    }
}