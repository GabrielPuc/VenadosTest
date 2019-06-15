package com.gd.venadostest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gd.venadostest.R;
import com.gd.venadostest.models.Players;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlayersAdapter extends BaseAdapter {

    private List<Players> playersData;
    private LayoutInflater mInflaterCatalogListItems;

    public PlayersAdapter(Context context, List<Players> playersData) {
        this.playersData = playersData;
        mInflaterCatalogListItems = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //This function will determine how many items to be displayed
    @Override
    public int getCount() {
        return playersData.size();
    }

    @Override
    public Object getItem(int position) {
        return playersData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = mInflaterCatalogListItems.inflate(R.layout.item_grid,
                    null);
            holder.player_name = convertView.findViewById(R.id.player_name);
            holder.player_position = convertView.findViewById(R.id.player_position);
            holder.player_image = convertView.findViewById(R.id.player_image);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        //Change the content here
        if (playersData.get(position) != null) {
            if(playersData.get(position).position != null){
                holder.player_position.setText(playersData.get(position).position);
            }else{
                holder.player_position.setText(playersData.get(position).role);
            }
            holder.player_name.setText(playersData.get(position).name);
            Picasso.get().load(playersData.get(position).image).into(holder.player_image);
        }

        return convertView;
    }

    //View Holder class used for reusing the same inflated view. It will decrease the inflation overhead @getView
    private static class ViewHolder {
        TextView player_position,player_name;
        ImageView player_image;

    }

}
