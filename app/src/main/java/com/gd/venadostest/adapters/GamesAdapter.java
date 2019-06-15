package com.gd.venadostest.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gd.venadostest.R;
import com.gd.venadostest.models.Game;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.MyViewHolder> {

    List<Game> gamesDataList;

    public GamesAdapter(List gamesDataList) {
        this.gamesDataList= gamesDataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_game_list, viewGroup, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Game data = gamesDataList.get(i);

        if(data.local){
            viewHolder.team_a_name.setText("Venados F.C.");
            viewHolder.team_a_image.setImageResource(R.drawable.venados_logo);
            viewHolder.team_b_name.setText(data.opponent);
            Picasso.get().load(data.opponent_image).into(viewHolder.team_b_image);
            //viewHolder.team_b_image.setImageResource(R.drawable.venados_logo);
        }else{
            viewHolder.team_b_name.setText("Venados F.C.");
            viewHolder.team_b_image.setImageResource(R.drawable.venados_logo);
            viewHolder.team_a_name.setText(data.opponent);
            Picasso.get().load(data.opponent_image).into(viewHolder.team_a_image);
            //viewHolder.team_a_image.setImageResource(R.drawable.venados_logo);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        try {
            Date date1 = dateFormat.parse(data.datetime);
            String[] dates = dateFormat.format(date1).split("-");
            String dateMatch = dates[1] + " " + getMonth(dates[0]);
            viewHolder.day.setText(dateMatch);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String scoreText = data.home_score + " - " + data.away_score;
        viewHolder.score.setText(scoreText);

    }


    private String getMonth(String month){
        switch (month){
            case "01":
                return "Enero";
            case "02":
                return "Febrero";
            case "03":
                return "Marzo";
            case "04":
                return "Abril";
            case "05":
                return "Mayo";
            case "06":
                return "Junio";
            case "07":
                return "Julio";
            case "08":
                return "Agosto";
            case "09":
                return "Septiembre";
            case "10":
                return "Octubre";
            case "11":
                return "Noviembre";
            case "12":
                return "Diciembre";

        }
        return "";
    }

    @Override
    public int getItemCount() {
        return gamesDataList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView day,score,team_a_name,team_b_name;
        ImageView team_a_image,team_b_image;
        ImageButton dateButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.match_date);
            score = itemView.findViewById(R.id.score);
            team_a_name= itemView.findViewById(R.id.team_a);
            team_a_image= itemView.findViewById(R.id.logo_a);
            team_b_name= itemView.findViewById(R.id.team_b);
            team_b_image= itemView.findViewById(R.id.logo_b);
            dateButton = itemView.findViewById(R.id.imageButton);

        }
    }
}