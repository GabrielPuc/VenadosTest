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
import com.gd.venadostest.adapters.StatsAdapter;
import com.gd.venadostest.models.Game;
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
public class StatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private StatsAdapter statsAdapter;
    private List statsDataList =new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    public StatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        recyclerView = view.findViewById(R.id.stats_screen__recycler);
        statsAdapter = new StatsAdapter(statsDataList);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(statsAdapter);
        //generateDummy();
        getStats();

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayoutStats);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getStats();
            }
        });

        return view;
    }


    public void getStats(){
        ApiInterface apiInterface = RetrofitUtils.getRestClient();
        apiInterface.getStats().enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.body().success){
                    statsDataList.clear();
                    List<Stat> stats = response.body().data.stats;

                    statsDataList.addAll(stats);

                    statsAdapter.notifyDataSetChanged();
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

}
