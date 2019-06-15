package com.gd.venadostest.fragments.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gd.venadostest.R;
import com.gd.venadostest.adapters.GamesAdapter;
import com.gd.venadostest.models.Game;
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
public class GamesFragment extends Fragment {

    private RecyclerView recyclerView;
    private GamesAdapter gamesAdapter;
    private List gamesDataList =new ArrayList<>();
    private String type = "";
    public static String COPA_MX = "Copa MX";
    public static String ASCENSO_MX = "Ascenso MX";
    private SwipeRefreshLayout swipeRefreshLayout;

    public GamesFragment() {
        // Required empty public constructor
    }


    public void setType(String type){
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games, container, false);

        recyclerView = view.findViewById(R.id.matches_screen__recycler);
        gamesAdapter = new GamesAdapter(gamesDataList);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(gamesAdapter);
        //generateDummy();
        getGames();

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGames();
            }
        });

        return view;
    }

    public void getGames(){
        ApiInterface apiInterface = RetrofitUtils.getRestClient();
        apiInterface.getGames().enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.body().success){
                    gamesDataList.clear();
                    List<Game> games = response.body().data.games;
                    List<Game> gamesLocal = new ArrayList<>();

                    for (int i = 0; i<games.size(); i++){
                        if(games.get(i).league.equals(type)){
                            gamesLocal.add(games.get(i));
                        }
                    }

                    gamesDataList.addAll(gamesLocal);

                    gamesAdapter.notifyDataSetChanged();
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

    public void generateDummy(){
        Game game = new Game(true,"Pachuca","Pachuca","2019-05-30T18:17:00+00:00","Copa MX","Venados", 2,2);
        Game game2 = new Game(false,"Pachuca","Pachuca","2019-05-30T18:17:00+00:00","Copa MX","Venados", 3,1);
        Game game3 = new Game(true,"Pachuca","Pachuca","2019-05-30T18:17:00+00:00","Copa MX","Venados", 2,2);
        Game game4 = new Game(false,"Pachuca","Pachuca","2019-05-30T18:17:00+00:00","Copa MX","Venados", 3,1);
        gamesDataList.add(game);
        gamesDataList.add(game2);
        gamesDataList.add(game3);
        gamesDataList.add(game4);
    }

}
