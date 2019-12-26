package com.example.youcan.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.youcan.R;
import com.google.android.material.tabs.TabLayout;

public class DashBoardFragment extends Fragment implements TabLayout.BaseOnTabSelectedListener {
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    private DashboardPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);


        tabLayout = view.findViewById(R.id.tablayout);

        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.addTab(tabLayout.newTab().setText("Daily View"));
        tabLayout.addTab(tabLayout.newTab().setText("Week View"));
        tabLayout.addTab(tabLayout.newTab().setText("Month View"));
        viewPager = view.findViewById(R.id.dashboard_pager);
        adapter = new DashboardPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);
        return view;

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
