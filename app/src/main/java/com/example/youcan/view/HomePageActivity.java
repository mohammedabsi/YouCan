package com.example.youcan.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.youcan.R;
import com.fxn.BubbleTabBar;
import com.fxn.OnBubbleClickListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mikhaellopez.circularimageview.CircularImageView;


public class HomePageActivity extends AppCompatActivity {
    private static final String MAIN_CHANNEL_ID = "com.example.youcan.MAIN_CHANNEL_ID";
    private DrawerLayout drawer;
    private int clickedNavItem = 0;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    DocumentReference userprofilenode = FirebaseFirestore.getInstance().collection("UserData").document(firebaseUser.getUid());
    private HomeFragment secondFragment;
    CircularImageView profile_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
       setContentView(R.layout.activity_home_container);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'> Home </font>"));
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        final RelativeLayout content = findViewById(R.id.content);

        toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'> Home </font>"));
        secondFragment = new HomeFragment();
        loadFragment(secondFragment);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        profile_photo = headerView.findViewById(R.id.profile_photo);
        TextView v2 = headerView.findViewById(R.id.usernameheader);
        TextView v3 = headerView.findViewById(R.id.email_header);
        v3.setText(firebaseUser.getEmail());
        userprofilenode.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                v2.setText(documentSnapshot.getString("username"));

                if (documentSnapshot.getString("imageprofile") != null) {

                    Glide

                            .with(HomePageActivity.this)
                            .load(documentSnapshot.getString("imageprofile"))
                            .into(profile_photo);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                float slideX = drawerView.getWidth() * slideOffset;
                content.setTranslationX(slideX);

            }
        };

        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final Snackbar snackbar = Snackbar
                        .make(drawer, "Coming On next Version", Snackbar.LENGTH_SHORT).setTextColor(getResources().getColor(R.color.lightgreen)).setBackgroundTint(getResources().getColor(R.color.white)).setAnchorView(R.id.bottomBar);
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        clickedNavItem = R.id.nav_profile;
                        break;
                    case R.id.nav_Challenges:
                        clickedNavItem = R.id.nav_Challenges;
                        break;

                    case R.id.nav_reminder:
                        snackbar.show();
                        clickedNavItem = R.id.nav_reminder;
                        break;
                        case R.id.nav_messages:
                            snackbar.show();
                        clickedNavItem = R.id.nav_messages;
                        break;
                    case R.id.nav_help:
                        snackbar.show();
                        clickedNavItem = R.id.nav_help;
                        break;
                    case R.id.nav_signout:
                        clickedNavItem = R.id.nav_signout;
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

                switch (clickedNavItem) {
                    case R.id.nav_profile:
                        startActivity(new Intent(HomePageActivity.this, ProfileActivity.class));
                        break;
                    case R.id.nav_Challenges:
                        startActivity(new Intent(HomePageActivity.this, ChallengesActivity.class));
                        break;
                    case R.id.nav_signout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
                        finish();
                        break;
                    case R.id.nav_reminder:
                        break;
                    case R.id.nav_messages:
                        break;
                        case R.id.nav_help:

                        break;


                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        BubbleTabBar bottomNav = findViewById(R.id.bottomBar);
        bottomNav.addBubbleListener(new OnBubbleClickListener() {


            @Override
            public void onBubbleClick(int i) {

                if (i == R.id.nav_home) {
                    toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'> Home </font>"));
                    loadFragment(new HomeFragment());
                } else if (i == R.id.nav_add_new) {

                    toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'>Activity </font>"));
                    loadFragment(new AddActivityFragment());
                } else if (i == R.id.nav_dashboard) {

                    toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'>Dashboard </font>"));
                    loadFragment(new DashBoardFragment());
                } else if (i == R.id.nav_settings) {
                    toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'>Settings </font>"));
                    loadFragment(new SettingsFragment());

                }


            }
        });
    }

    private void createNotificationChannel() {
        Uri ringtone_notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(
                    MAIN_CHANNEL_ID,
                    getString(R.string.Reminder),
                    importance);
            channel.setShowBadge(true);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            channel.setSound(ringtone_notification, audioAttributes);
            channel.setVibrationPattern(new long[]{200, 500, 0, 0});
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(channel);
        }
    }

    public void showBasicNotification() {

        Uri ringtone_notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, MAIN_CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher_notification)
                        .setContentTitle("Water Reminder")
                        .setContentText("Dont Forget to drink water today")
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setSound(ringtone_notification)
                        .setAutoCancel(true)
                        .setLights(Color.CYAN, 700, 700)
                        .setVibrate(new long[]{200, 500, 0, 0});

        Notification notification = mBuilder.build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, notification);
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification_menu_item:
                //todo : handle this notification at time the user set his reminder
                showBasicNotification();
                Toast.makeText(HomePageActivity.this, "Notification Clicked", Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }


}

