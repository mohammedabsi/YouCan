package com.example.youcan.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.youcan.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class AddActivityFragment extends Fragment  {

    ConstraintLayout steps_intake_constraint ,waterintake_constraint , calories_constraint ,weight_constraint ,heartrate_constraint ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.addnew_fragment, container, false);
        steps_intake_constraint = view.findViewById(R.id.steps_intake_constraint);
        waterintake_constraint = view.findViewById(R.id.waterintake_constraint);
        calories_constraint = view.findViewById(R.id.calories_constraint);
        weight_constraint = view.findViewById(R.id.weight_constraint);
        heartrate_constraint = view.findViewById(R.id.heartrate_constraint);



        steps_intake_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepsBottomSheet bottomSheet = new StepsBottomSheet();
                bottomSheet.show(getFragmentManager() ,"bottomsheet");
            }
        });
        waterintake_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WaterCupBottomSheet bottomSheet = new WaterCupBottomSheet();
                bottomSheet.show(getFragmentManager() ,"bottomsheet");
            }
        });

        weight_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeightIntakeBottomSheet bottomSheet = new WeightIntakeBottomSheet();
                bottomSheet.show(getFragmentManager() ,"bottomsheet");
            }
        });

        calories_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddRecipeActivity.class));
            }
        });
        heartrate_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Next Update")
                        .setMessage("Coming soon on next version")
                        .setPositiveButton("Ok", null)
                        .setIcon(R.drawable.icon_app)
                        .show();
            }
        });

        return view;
    }
    @Override
    public void onStart() {

        super.onStart();
    }




}
