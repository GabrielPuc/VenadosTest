package com.gd.venadostest.fragments.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gd.venadostest.R;
import com.gd.venadostest.adapters.PlayersAdapter;
import com.gd.venadostest.models.Players;
import com.gd.venadostest.models.Stat;
import com.gd.venadostest.network.ApiInterface;
import com.gd.venadostest.network.responses.BasicResponse;
import com.gd.venadostest.utils.RetrofitUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment implements AdapterView.OnItemClickListener {


    private List<Players> players = new ArrayList<>();
    private PlayersAdapter playersAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public PlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        Toolbar toolbar = view.findViewById(R.id.players_screen_toolbar);
        toolbar.setTitle("Jugadores");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        GridView gridview = view.findViewById(R.id.players_grid);
        playersAdapter = new PlayersAdapter(getActivity(),players);
        gridview.setAdapter(playersAdapter);
        gridview.setNumColumns(3);
        //gridview.setOnClickListener(this);
        gridview.setOnItemClickListener(this);

        getPlayers();

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayoutPlayers);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPlayers();
            }
        });

        return view;
    }


    public void getPlayers(){
        ApiInterface apiInterface = RetrofitUtils.getRestClient();
        apiInterface.getPlayers().enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.body().success){
                    players.clear();
                    List<Players> centers = response.body().data.team.centers;
                    List<Players> coaches = response.body().data.team.coaches;
                    List<Players> defenses = response.body().data.team.defenses;
                    List<Players> forwards = response.body().data.team.forwards;
                    List<Players> goalkeepers = response.body().data.team.goalkeepers;

                    players.addAll(centers);
                    players.addAll(coaches);
                    players.addAll(defenses);
                    players.addAll(forwards);
                    players.addAll(goalkeepers);

                    playersAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.d("game","failure " + t.getLocalizedMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    TextView name,positionP,birthday,birthplace,weight,heightP,last_team;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Players player = players.get(position);

        LayoutInflater inflater = (LayoutInflater)
                getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.window_player_detail, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        Picasso.get().load(player.image).into((ImageView) popupView.findViewById(R.id.popup_player_image));

        name = popupView.findViewById(R.id.popup_player_name);
        name.setText(player.name);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date1 = dateFormat.parse(player.birthday);
            String dates = dateFormat.format(date1);
            birthday = popupView.findViewById(R.id.popup_player_birthday);
            birthday.setText(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        birthplace = popupView.findViewById(R.id.popup_player_birthplace);
        birthplace.setText(player.birth_place);

        weight = popupView.findViewById(R.id.popup_player_weight);
        weight.setText(String.valueOf(player.weight));

        heightP = popupView.findViewById(R.id.popup_player_height);
        heightP.setText(String.valueOf(player.height));

        last_team = popupView.findViewById(R.id.popup_player_last_team);
        last_team.setText(player.last_team);

        positionP = popupView.findViewById(R.id.popup_player_position);
        if(player.position != null){
            positionP.setText(player.position);
        }else{
            positionP.setText(player.role);
        }

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

    }

}
