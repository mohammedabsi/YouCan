package com.example.youcan.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.youcan.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Discover_SeeAll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        Toolbar toolbar = findViewById(R.id.discover_toolbar);
        toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'> Discover </font>"));
        toolbar.setNavigationIcon(R.drawable.ic_back_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Discover_SeeAll.this, HomePageActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        new MaterialAlertDialogBuilder(Discover_SeeAll.this)
                .setTitle("Next Update")
                .setMessage("Coming soon on next version")
                .setPositiveButton("Ok", null)
                .setIcon(R.drawable.icon_app)
                .show();
        super.onStart();
    }
}
