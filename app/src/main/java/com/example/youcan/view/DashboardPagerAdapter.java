package com.example.youcan.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DashboardPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public DashboardPagerAdapter(@NonNull FragmentManager fm , int tabCount) {
        super(fm);
        this.tabCount= tabCount;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DailyViewFragment();
            case 1:

                return new WeeklyViewFragment();
            case 2:

                return new MonthViewFragment();
            default:
                return null;

    }}

    @Override
    public int getCount() {
            return tabCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:

                return "Daily View";
            case 1:

                return "Weekly View";
            case 2:

                return "Monthly View";
            default:
                return super.getPageTitle(position);

        }

    }
}
