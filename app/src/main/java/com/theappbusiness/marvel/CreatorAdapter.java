package com.theappbusiness.marvel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.theappbusiness.marvel.database.Creator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demir on 7.04.2017.
 */

public class CreatorAdapter extends BaseAdapter {

    List<Creator> list = new ArrayList<>();
    LayoutInflater inflater;
    Activity activity;

    public CreatorAdapter(Activity activity)
    {
        this.activity = activity;
        inflater = LayoutInflater.from(this.activity);
    }

    public void updateList(List<Creator> creatorList)
    {
        list.clear();
        list.addAll(creatorList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Creator getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {

            convertView = inflater.inflate(R.layout.row_creator, parent, false);

            holder = new ViewHolder();

            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvRole = (TextView) convertView.findViewById(R.id.tvRole);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }



        final Creator creator = getItem(position);

        holder.tvName.setText(creator.getName());
        holder.tvRole.setText(creator.getRole());



        return convertView;
    }

    private static class ViewHolder {
        public TextView tvRole;
        public TextView tvName;
    }

}
