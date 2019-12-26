package com.example.youcan.view.personalInfo.personalInfoFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youcan.R;
import com.example.youcan.view.personalInfo.PersonalInfoInterface;

public class HeightFragment extends Fragment {
    PersonalInfoInterface personalInfoInterface;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof PersonalInfoInterface){
            personalInfoInterface= (PersonalInfoInterface) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.height_fragment, container, false);
        final NumberPicker picker=view.findViewById(R.id.height_numberPicker);
        picker.setMinValue(100);
        picker.setMaxValue(200);
        picker.setValue(150);
        Button continueButton=view.findViewById(R.id.question4_btn);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personalInfoInterface.getHeight(String.valueOf(picker.getValue()));
            }
        });

        return view;
    }
}
