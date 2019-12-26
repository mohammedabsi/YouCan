package com.example.youcan.view.personalInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.youcan.view.HomePageActivity;
import com.example.youcan.R;
import com.example.youcan.view.personalInfo.personalInfoFragment.FinishAskingFragment;
import com.example.youcan.view.personalInfo.personalInfoFragment.GenderFragment;
import com.example.youcan.view.personalInfo.personalInfoFragment.GoalWeightFragment;
import com.example.youcan.view.personalInfo.personalInfoFragment.HeightFragment;
import com.example.youcan.view.personalInfo.personalInfoFragment.NameQuestionFragment;
import com.example.youcan.view.personalInfo.personalInfoFragment.WeightFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

public class PersonlInfoActivity extends AppCompatActivity implements PersonalInfoInterface {
    FragmentManager fragmentManager;
    ProgressBar progressBar;
    String name, password;
    String height;
    String weight;
    String goalweight;
    Boolean gender;
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String GOAL_WEIGHT = "goalweight";
    public static final String GENDER = "gender";
    public static final String USER_NAME = "username";
    public static final String USER_PASSWORD = "userpassword";
    public static final String USER_PHONE = "userphone";
    public static final String USER_BMI = "userbmi";
    public static final String USER_ID = "userid";
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference userprofilenode = db.collection("UserData").document(firebaseUser.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personl_info_pager);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.personal_info_container, new NameQuestionFragment()).commit();
        progressBar = findViewById(R.id.progress_personal_info);

    }


    @Override
    public void getName(String name, String password) {
        this.name = name;
        this.password = password;
        fragmentManager.beginTransaction().replace(R.id.personal_info_container, new GenderFragment()).commit();
        progressBar.setProgress(32);

    }

    @Override
    public void getGender(Boolean gender) {
        this.gender = gender;
        fragmentManager.beginTransaction().replace(R.id.personal_info_container, new HeightFragment()).commit();
        progressBar.setProgress(48);
    }


    @Override
    public void getHeight(String height) {
        this.height = height;
        fragmentManager.beginTransaction().replace(R.id.personal_info_container, new WeightFragment()).commit();
        progressBar.setProgress(64);
    }

    @Override
    public void getWeight(String weight) {
        this.weight = weight;

        Bundle bundle=new Bundle();
        bundle.putString(USER_NAME ,name);
        bundle.putString(HEIGHT,height);
        bundle.putString(WEIGHT,weight);
        bundle.putBoolean(GENDER,gender);
        bundle.putString(GOAL_WEIGHT,goalweight);
        GoalWeightFragment goalWeightFragment = new GoalWeightFragment();
        goalWeightFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.personal_info_container, goalWeightFragment).commit();
        progressBar.setProgress(77);

    }

    @Override
    public void getGoalWeight(String  goalWeight) {
        this.goalweight = goalWeight;
        progressBar.setVisibility(View.GONE);
        Hawk.init(this).build();
        Map<String, Object> user = new HashMap<>();
        user.put(USER_NAME, name);
        user.put(USER_PASSWORD, password);
        user.put(USER_PHONE, Hawk.get("mobile"));
        user.put(HEIGHT, height);
        user.put(WEIGHT, weight);
        user.put(GENDER, gender);
        user.put(GOAL_WEIGHT,goalweight);

        double h = Double.parseDouble(height);
        double w = Double.parseDouble(weight);
        String x = String.valueOf(w/((h*h)/10000));


        user.put(USER_BMI, x);
        user.put(USER_ID, firebaseUser.getUid());


        userprofilenode.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(PersonlInfoActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PersonlInfoActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
       // GoalWeightFragment goalWeightFragment = new GoalWeightFragment();

        fragmentManager.beginTransaction().replace(R.id.personal_info_container, new FinishAskingFragment()).commit();


    }

    @Override
    public void finishAsking() {
        startActivity(new Intent(this, HomePageActivity.class));
        finish();
    }


}
