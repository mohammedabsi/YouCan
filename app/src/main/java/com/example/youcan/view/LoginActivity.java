package com.example.youcan.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.youcan.R;
import com.example.youcan.view.signup.SignUpActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    TextInputEditText password_login, username_login;

    ProgressBar progressBar2;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    //FirebaseAuth firebaseAuth;
  //  FirebaseUser mauth = FirebaseAuth.getInstance().getCurrentUser();
//    String current_user_id = mauth.getUid();

   // FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance() ;
    TextInputLayout username_in, password_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);



        //hide notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username_login = findViewById(R.id.username_login);
        password_login = findViewById(R.id.password_login);
        progressBar2 = findViewById(R.id.progressBarlogin);
        username_in = findViewById(R.id.username_in);
        password_in = findViewById(R.id.password_in);



    }

    public void returnRegister(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void goHome(View view) {



//        firebaseFirestore.collection("UserData").document(current_user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.getResult().exists()){
//                    Toast.makeText(LoginActivity.this, "hey", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(LoginActivity.this, "no current user", Toast.LENGTH_SHORT).show();
//
//                }    }
//        });
//
//        Hawk.init(this).build();
//        String password =   Hawk.get("codesent");
//        String phone =  "+972592731394";
//
//        progressBar2.setVisibility(View.VISIBLE);
//        if (username_login.getText().toString().trim().equals(phone) && password_login.getText().toString().trim().equals(password)) {
//            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
//            startActivity(intent);
//            finish();
//        } else if (username_login.getText().toString().equals("")) {
//            progressBar2.setVisibility(View.INVISIBLE);
//            username_in.setError("Enter Correct Name !!");
//            username_in.setErrorEnabled(true);
//            password_in.setError("");
//            password_in.setErrorEnabled(false);
//        } else if (password_login.getText().toString().equals("")) {
//            progressBar2.setVisibility(View.INVISIBLE);
//            password_in.setError("Enter Correct password !!");
//            password_in.setErrorEnabled(true);
//            username_in.setError("");
//            username_in.setErrorEnabled(false);
//
//
//        } else if (!username_login.getText().toString().trim().equals(phone) && !password_login.getText().toString().trim().equals(password)) {
//            progressBar2.setVisibility(View.INVISIBLE);
//            password_in.setError("Wrong Password !!");
//            password_in.setErrorEnabled(true);
//            username_in.setError("Wrong user name");
//            username_in.setErrorEnabled(true);
////                    Toast.makeText(LoginActivity.this, "Wrong Data Entered !! ", Toast.LENGTH_SHORT).show();
//        } else if (username_login.getText().toString().trim().equals(name) && !password_login.getText().toString().trim().equals(password)) {
//            progressBar2.setVisibility(View.INVISIBLE);
//            password_in.setError("Wrong Password !!");
//            password_in.setErrorEnabled(true);
//            username_in.setError("");
//            username_in.setErrorEnabled(false);
//        }else if (!username_login.getText().toString().trim().equals(phone) && password_login.getText().toString().trim().equals(password)) {
//            progressBar2.setVisibility(View.INVISIBLE);
//            password_in.setError("");
//            password_in.setErrorEnabled(false);
//            username_in.setError("Wrong Name !!");
//            username_in.setErrorEnabled(true);
//        }
//
//        else {
//            progressBar2.setVisibility(View.INVISIBLE);
//            Toast.makeText(LoginActivity.this, "This account is not found !! ", Toast.LENGTH_SHORT).show();
//
//        }


    }


    public void loginWithFacebook(View view) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        progressBar2.setVisibility(View.VISIBLE);

        if (firebaseUser == null) {


            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.FacebookBuilder().build()
            );


            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setTheme(R.style.AppTheme)
                            .setAvailableProviders(providers).build(),
                    RC_SIGN_IN
            );
        } else {
            startActivity(new Intent(this, HomePageActivity.class));
            finish();
            Toast.makeText(this, "login succes", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginWithGmail(View view) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        progressBar2.setVisibility(View.VISIBLE);

        if (firebaseUser == null) {

            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.GoogleBuilder().build()

            );

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setTheme(R.style.AppTheme)
                            .setAvailableProviders(providers).build(),
                    RC_SIGN_IN
            );
        } else {
            startActivity(new Intent(this, HomePageActivity.class));
            finish();
            Toast.makeText(this, "login succes", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("WrongConstant")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (firebaseUser != null) {
                    startActivity(new Intent(this, HomePageActivity.class));
                    Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
                }

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                if (response.getError().getErrorCode() == 1) {
                    Toast.makeText(this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show();

                }

                //  startActivity(new Intent(this, LoginActivity.class));

            }
        }


    }

    @Override
    protected void onStart() {

        super.onStart();
    }
}
