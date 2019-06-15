package com.gd.venadostest.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gd.venadostest.R;
import com.gd.venadostest.adapters.TabAdapter;
import com.gd.venadostest.fragments.tabs.GamesFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = view.findViewById(R.id.home_screen__toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.home_tabs);

        adapter = new TabAdapter(getFragmentManager());

        GamesFragment gamesFragment = new GamesFragment();
        gamesFragment.setType(GamesFragment.COPA_MX);

        GamesFragment gamesFragmentB = new GamesFragment();
        gamesFragmentB.setType(GamesFragment.ASCENSO_MX);

        adapter.addFragment(gamesFragment, "COPA MX");
        adapter.addFragment(gamesFragmentB, "ASCENSO MX");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
