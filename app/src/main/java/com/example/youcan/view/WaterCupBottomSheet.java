package com.example.youcan.view;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.example.youcan.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class WaterCupBottomSheet extends BottomSheetDialogFragment {
    private LottieAnimationView animationView;

    TextView cupcounter, cupcounter2;
    int counter = 0;
    Button incremenetbtn, decrementbtn ,update_water;
    boolean x = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.water_cup_bottomsheet, container, false);

        animationView = view.findViewById(R.id.animation_view);
        cupcounter = view.findViewById(R.id.cupcounter);
        cupcounter2 = view.findViewById(R.id.cupcounter2);
        incremenetbtn = view.findViewById(R.id.incremenetbtn);
        decrementbtn = view.findViewById(R.id.decrementbtn);
        update_water = view.findViewById(R.id.update_water);

        incremenetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncrementCup();
                if (x == false) {
                    animationView.playAnimation();
                } else {
                    animationView.reverseAnimationSpeed();
                    x = !x;
                }


            }
        });
        decrementbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter > 0) {
                    DecrementCup();
                    if (x == true) {
                        animationView.playAnimation();
                    } else {
                        animationView.reverseAnimationSpeed();
                        x = !x;
                    }
                } else {
                    Toast.makeText(getContext(), "0 Cups!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        update_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo : save cups to firebase to use it at dashboard daily view later
            }
        });
        CupColor();
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);

    }

    public void CupColor() {

        SimpleColorFilter filter = new SimpleColorFilter(getResources().getColor(R.color.cupcolor));
        KeyPath keyPath = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filter);
        animationView.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);
    }

    public void DecrementCup() {
        if (counter > 0) {
            counter--;
            cupcounter.setText(Integer.toString(counter));
            cupcounter2.setText(Integer.toString(counter));
            if (counter <=8){
                cupcounter2.setTextColor(Color.BLACK);
            }

        }

    }

    public void IncrementCup() {


        if (counter >= 0) {
            counter++;
            cupcounter.setText(Integer.toString(counter));
            cupcounter2.setText(Integer.toString(counter));
            if (counter >8){
                cupcounter2.setTextColor(Color.RED);
            }

        }
    }





}
