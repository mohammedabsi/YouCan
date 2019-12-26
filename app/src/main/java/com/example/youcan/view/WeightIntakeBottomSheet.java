package com.example.youcan.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youcan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

import ke.tang.ruler.RulerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeightIntakeBottomSheet extends BottomSheetDialogFragment {
RulerView weight_ruler ,suggest_wieght_ruler;
TextView ideal_goal_weight ,bmi;
Button update_weight;
ProgressBar progressBarweight;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    DocumentReference userprofilenode = FirebaseFirestore.getInstance().collection("UserData").document(firebaseUser.getUid());
    public WeightIntakeBottomSheet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weight_intake_bottomsheet, container, false);
        weight_ruler = view.findViewById(R.id.weight_ruler);
        suggest_wieght_ruler = view.findViewById(R.id.suggest_wieght_ruler);
        ideal_goal_weight = view.findViewById(R.id.ideal_goal_weight);
        update_weight = view.findViewById(R.id.update_weight);
        bmi = view.findViewById(R.id.bmi);
        progressBarweight = view.findViewById(R.id.progressBarweight);
        progressBarweight.setVisibility(View.VISIBLE);


        userprofilenode.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                weight_ruler.setValue(Integer.parseInt(documentSnapshot.getString("weight")));
                suggest_wieght_ruler.setValue(Integer.parseInt(documentSnapshot.getString("goalweight")));
                ideal_goal_weight.setText(String.valueOf(suggest_wieght_ruler.getValue()));
               double x = Double.parseDouble(documentSnapshot.getString("userbmi"));
                bmi.setText(String.format(Locale.ENGLISH,"%.2f" , x));
                progressBarweight.setVisibility(View.INVISIBLE);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBarweight.setVisibility(View.INVISIBLE);

            }
        });

update_weight.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //todo : update weight and goal weight on firebase and update BMI (weight/height*height)
    }
});


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
        super.onCreate(savedInstanceState);
    }

}
