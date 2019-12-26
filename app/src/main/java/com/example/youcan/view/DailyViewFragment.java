package com.example.youcan.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.youcan.R;
import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyViewFragment extends Fragment{

    ConstraintLayout daily_heart_rate_layout;
    TextView daily_steps_duration_txt;



    public DailyViewFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_view_fragment, container, false);
        //  you check if your app is already attempting to authorize against the Fitness API




        daily_steps_duration_txt = view.findViewById(R.id.daily_steps_duration_txt);
        daily_heart_rate_layout = view.findViewById(R.id.daily_heart_rate_layout);
        daily_heart_rate_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar
                        .make(daily_heart_rate_layout, "Coming On next Version", Snackbar.LENGTH_SHORT).setTextColor(getResources().getColor(R.color.lightgreen)).setBackgroundTint(getResources().getColor(R.color.white)).setAnchorView(R.id.bottomBar);
                snackbar.show();
            }
        });


        return view;
    }

}
