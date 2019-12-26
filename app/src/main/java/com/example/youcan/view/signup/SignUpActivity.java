package com.example.youcan.view.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.youcan.R;
import com.example.youcan.view.personalInfo.PersonlInfoActivity;
import com.example.youcan.view.signup.signupfragment.MobileNumberSignUpFragment;
import com.example.youcan.view.signup.signupfragment.MobileNumberVerificationFragment;
import com.example.youcan.view.signup.signupfragment.ResendMobileNumberFragment;
import com.example.youcan.view.signup.signupfragment.StartingPersonnalInfoFragment;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements SignUpInterface {
    FragmentManager fragmentManager;
    MobileNumberSignUpFragment mobileNumberSignUpFragment;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_container);
        mobileNumberSignUpFragment = new MobileNumberSignUpFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.sign_up_container_fragment, mobileNumberSignUpFragment).commit();

        mAuth = FirebaseAuth.getInstance();
    }



    @Override
    public void getMobileNumber() {


        ///go to next step sign_up verification

        fragmentManager.beginTransaction().replace(R.id.sign_up_container_fragment, new MobileNumberVerificationFragment()).commit();
    }

    @Override
    public void finishVerification() {
        fragmentManager.beginTransaction().replace(R.id.sign_up_container_fragment, new StartingPersonnalInfoFragment()).commit();
    }

    @Override
    public void resendMobileNumber() {
        fragmentManager.beginTransaction().replace(R.id.sign_up_container_fragment, new ResendMobileNumberFragment()).commit();
        Toast.makeText(this, "resend mobile number", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startAskPersonalInformation() {
        startActivity(new Intent(this, PersonlInfoActivity.class));
        finish();

    }



}
