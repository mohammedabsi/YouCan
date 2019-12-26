package com.example.youcan.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youcan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity  {

    private static int Splash_Time_Out = 4000;
    Handler handler = new Handler();
FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);



        FrameLayout frameLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) frameLayout.getBackground();
        animationDrawable.setEnterFadeDuration(0);
        animationDrawable.setExitFadeDuration(1500);

        animationDrawable.start();

        //handler for splash to run for 4 seconds
        handler.postDelayed(runnable, Splash_Time_Out);
    }

    // عمل الrunnable   برة الon create  عشان تعرف توصللها في On Pause
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (getIntent().getExtras() == null){
            Intent intent = new Intent(SplashScreen.this, OnBoardActivity.class);
            startActivity(intent);
            finish();
            }else {
                if (firebaseUser !=null){
                    Intent intent = new Intent(SplashScreen.this, HomePageActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
                }
            }
        }

    };



    @Override
    protected void onPause() {
        // to stop the runnable when app is paused
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    @Override
    protected void onRestart() {
        // to stop the runnable when app is paused
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, Splash_Time_Out);
        super.onRestart();
    }


}
