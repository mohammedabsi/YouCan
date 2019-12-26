package com.example.youcan.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.youcan.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ChallengesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenges_fragment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'> Challenges </font>"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_button));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChallengesActivity.this , HomePageActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        new MaterialAlertDialogBuilder(ChallengesActivity.this)
                .setTitle("Next Update")
                .setMessage("Coming soon on next version")
                .setPositiveButton("Ok", null)
                .setIcon(R.drawable.icon_app)
                .show();
        super.onStart();
    }
}
