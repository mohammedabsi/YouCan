package com.example.youcan.view;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youcan.R;
import com.example.youcan.view.AddRecipeActivity;
import com.example.youcan.view.Discover_SeeAll;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class HomeFragment extends Fragment {
    Thread thread;
    View[]runProgress;
    View[]goalProgress;
           TextView textView ,txt2 , home_running_duration_txt ,home_goals_txt , home_name_txt ,home_bmi_value;
    LinearLayout home_linearlayout ;
ProgressBar progressBarHome;
ImageView recipes_ic_home_image ,home_bmi_pointer;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    DocumentReference userprofilenode = FirebaseFirestore.getInstance().collection("UserData").document(firebaseUser.getUid());


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.home_fragment, container, false);
        textView = view.findViewById(R.id.home_see_all);
        home_name_txt = view.findViewById(R.id.home_name_txt);
        home_goals_txt = view.findViewById(R.id.home_goals_txt);
        txt2 = view.findViewById(R.id.home_name_txt);
        home_running_duration_txt = view.findViewById(R.id.home_running_duration_txt);
        home_bmi_value = view.findViewById(R.id.home_bmi_value);
        recipes_ic_home_image = view.findViewById(R.id.recipes_ic_home_image);
        home_bmi_pointer = view.findViewById(R.id.home_bmi_pointer);
        progressBarHome = view.findViewById(R.id.progressBarHome);
        progressBarHome.setVisibility(View.VISIBLE);

       userprofilenode.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
           @Override
           public void onSuccess(DocumentSnapshot documentSnapshot) {
                double x = Double.parseDouble(documentSnapshot.getString("userbmi"));
               home_bmi_value.setText(String.format(Locale.ENGLISH,"%.2f" , x));


                                       if (x < 18.5) {
                                           ObjectAnimator animation = ObjectAnimator.ofFloat(home_bmi_pointer, "translationX", 50.0f);
                                           ObjectAnimator animation2 = ObjectAnimator.ofFloat(home_bmi_value, "translationX", 50.0f);
                                           animation.setDuration(450);
                                           animation2.setDuration(600);
                                           animation.start();
                                           animation2.start();
                                       } else
                                       if (x >= 18.5 && x < 24.9) {


                                           ObjectAnimator animation = ObjectAnimator.ofFloat(home_bmi_pointer, "translationX", 350.0f);
                                           ObjectAnimator animation2 = ObjectAnimator.ofFloat(home_bmi_value, "translationX", 350.0f);
                                           animation.setDuration(450);
                                           animation2.setDuration(600);
                                           animation.start();
                                           animation2.start();

                                       }
                                       else

                                       if (x >= 25 && x < 29.9) {
                                           ObjectAnimator animation = ObjectAnimator.ofFloat(home_bmi_pointer, "translationX", 650.0f);
                                           ObjectAnimator animation2 = ObjectAnimator.ofFloat(home_bmi_value, "translationX", 650.0f);
                                           animation.setDuration(450);
                                           animation2.setDuration(600);
                                           animation.start();
                                           animation2.start();

                                       }

                                       else if (x >= 30) {
                                           ObjectAnimator animation = ObjectAnimator.ofFloat(home_bmi_pointer, "translationX", 950.0f);
                                           ObjectAnimator animation2 = ObjectAnimator.ofFloat(home_bmi_value, "translationX", 950.0f);
                                           animation.setDuration(450);
                                           animation2.setDuration(600);
                                           animation.start();
                                           animation2.start();

                                       }


           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {

           }
       });
//        double x =
////        home_bmi_pointer.setTranslationX();

        runProgress= new View[]{view.findViewById(R.id.v1),
                view.findViewById(R.id.v2),
                view.findViewById(R.id.v3),
                view.findViewById(R.id.v4),
                view.findViewById(R.id.v5),
                view.findViewById(R.id.v6),
                view.findViewById(R.id.v7),
                view.findViewById(R.id.v8),
                view.findViewById(R.id.v9),
                view.findViewById(R.id.v10),
                view.findViewById(R.id.v11),
                view.findViewById(R.id.v12),
                view.findViewById(R.id.v13),
                view.findViewById(R.id.v14),
                view.findViewById(R.id.v15),
                view.findViewById(R.id.v16),
                view.findViewById(R.id.v17),
                view.findViewById(R.id.v18),
                view.findViewById(R.id.v19),
                view.findViewById(R.id.v20),
                view.findViewById(R.id.v21),
                view.findViewById(R.id.v22),
                view.findViewById(R.id.v23),
                view.findViewById(R.id.v24)};
        goalProgress= new View[]{view.findViewById(R.id.va),
                view.findViewById(R.id.vb),
                view.findViewById(R.id.vc),
                view.findViewById(R.id.vd),
                view.findViewById(R.id.ve),
                view.findViewById(R.id.vf),
                view.findViewById(R.id.vg),
                view.findViewById(R.id.vh),
                view.findViewById(R.id.vi),
                view.findViewById(R.id.vj),
                view.findViewById(R.id.vk),
                view.findViewById(R.id.vl),
                view.findViewById(R.id.vm),
                view.findViewById(R.id.vn),
                view.findViewById(R.id.vo),
                view.findViewById(R.id.vp),
                view.findViewById(R.id.vq),
                view.findViewById(R.id.vr),
                view.findViewById(R.id.vs),
                view.findViewById(R.id.vt),
                view.findViewById(R.id.vu),
                view.findViewById(R.id.vv),
                view.findViewById(R.id.vw),
                view.findViewById(R.id.vx)};
        //todo : here pass the values from google fit api later
        setRunProgressAnimation(30,25);
        setGoalProgressAnimation(30,15);
        userprofilenode.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                home_name_txt.setText(documentSnapshot.getString("username"));
                progressBarHome.setVisibility(View.INVISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        recipes_ic_home_image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddRecipeActivity.class));

            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        home_linearlayout = view.findViewById(R.id.home_linearlayout);

        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            startActivity(new Intent(getActivity(), Discover_SeeAll.class));
            }
        });
        return view;
    }


    private void setRunProgressAnimation(final double actual, final double done) {

        thread=  new Thread(new Runnable() {
            @Override
            public void run() {
                int fillRatio= (int) Math.ceil(((done/actual)*24));
                for (int i=0;i<fillRatio;i++){
                    try {
                        Thread.sleep(50);
                        final int finalI = i;
                        if(getActivity() == null)
                            return;

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isAdded()){
                                runProgress[finalI].setBackground(getResources().getDrawable(R.drawable.active_dot_red));
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
    private void setGoalProgressAnimation(final double actual, final double done) {

      thread =   new Thread(new Runnable() {
            @Override
            public void run() {
                int fillRatio= (int) Math.ceil(((done/actual)*24));
                for (int i=0;i<fillRatio;i++){
                    try {
                        Thread.sleep(50);
                        final int finalI = i;
                        if(getActivity() == null)
                            return;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(isAdded()){
                                goalProgress[finalI].setBackground(getResources().getDrawable(R.drawable.active_dot_primary));
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
     thread.start();
    }




    @Override
    public void onDestroy() {
       thread.interrupt();
        super.onDestroy();
    }
}
