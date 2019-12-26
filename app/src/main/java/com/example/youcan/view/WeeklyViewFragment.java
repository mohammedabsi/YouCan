package com.example.youcan.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.youcan.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyViewFragment extends Fragment {


    public WeeklyViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.week_view_fragment, container, false);
    }

}
