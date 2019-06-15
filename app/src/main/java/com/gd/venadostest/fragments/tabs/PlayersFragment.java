package com.gd.venadostest.fragments.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.gd.venadostest.R;
import com.gd.venadostest.adapters.PlayersAdapter;
import com.gd.venadostest.models.Players;
import com.gd.venadostest.models.Stat;
import com.gd.venadostest.network.ApiInterface;
import com.gd.venadostest.network.responses.BasicResponse;
import com.gd.venadostest.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), players.get(position).name,
                Toast.LENGTH_SHORT).show();
    }

}
