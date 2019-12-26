package com.example.youcan.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youcan.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddManualBottomSheet extends BottomSheetDialogFragment {
    //todo : add meal manually to recipes lists and update the macros on progress cardview


    public AddManualBottomSheet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_manual_bottom_sheet, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
        super.onCreate(savedInstanceState);
    }
}
