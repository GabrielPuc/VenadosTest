package com.gd.venadostest.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gd.venadostest.R;
import com.gd.venadostest.models.Game;
import com.gd.venadostest.models.Stat;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.MyViewHolder> {

    List<Stat> statsDataList;

    public StatsAdapter(List gamesDataList) {
        this.statsDataList= gamesDataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_stat_list, viewGroup, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Stat data = statsDataList.get(i);
        Picasso.get().load(data.image).into(viewHolder.team_image);
        viewHolder.pts_text.setText(""+data.points);
        viewHolder.dg_text.setText(""+data.score_diff);
        viewHolder.place_text.setText(""+data.position);
        viewHolder.jj_text.setText(""+data.games);
        viewHolder.team_name.setText(""+data.team);

    }


    @Override
    public int getItemCount() {
        return statsDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView place_text,team_name,jj_text,dg_text,pts_text;
        ImageView team_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            place_text = itemView.findViewById(R.id.place_text);
            team_name = itemView.findViewById(R.id.team_name);
            jj_text = itemView.findViewById(R.id.jj_text);
            dg_text = itemView.findViewById(R.id.dg_text);
            pts_text = itemView.findViewById(R.id.pts_text);
            team_image = itemView.findViewById(R.id.team_image);
        }
    }
}