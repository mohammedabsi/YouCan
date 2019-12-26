package com.example.youcan.view.signup.signupfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youcan.R;
import com.example.youcan.view.LoginActivity;
import com.example.youcan.view.signup.SignUpInterface;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
import com.orhanobut.hawk.Hawk;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.app.Activity.RESULT_OK;


public class MobileNumberSignUpFragment extends Fragment {
    SignUpInterface signUpInterface;
    CountryCodePicker ccp;
    TextView social_media_signup;
    private static final int RC_SIGN_IN = 100;
    String codesent ;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();




    public MobileNumberSignUpFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SignUpInterface) {
            signUpInterface = (SignUpInterface) context;
            Hawk.init(getContext()).build();

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_page_fragment, container, false);


        final TextView go_login = view.findViewById(R.id.go_login);
        ccp = view.findViewById(R.id.ccp);
        social_media_signup = view.findViewById(R.id.social_media_signup);
        social_media_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignUpUI();

            }
        });
        final EditText getnumber = view.findViewById(R.id.editText_carrierNumber);
        Button continueButton = view.findViewById(R.id.continue_tbn);

        //concat prefix with the number
        ccp.registerCarrierNumberEditText(getnumber);


        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));

            }
        });


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final String mobile = ccp.getFormattedFullNumber().trim();
                ccp.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
                    @Override
                    public void onValidityChanged(boolean isValidNumber) {
                        if (!isValidNumber) {
                            getnumber.setError("Invalid number");
                            return;
                        }
                        Hawk.put("mobile" ,mobile);
                        Toast.makeText(getContext(), "your number: "+mobile, Toast.LENGTH_SHORT).show();
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                mobile ,
                                30 ,
                                TimeUnit.SECONDS,
                                getActivity(),
                                mcallbacks);
                        signUpInterface.getMobileNumber();


                    }
                });

            }
        });
        return view;
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codesent = s ;
            Hawk.put( "codesent", codesent);

        }
    };
    private void showSignUpUI() {
        if (firebaseUser == null) {
            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                    new AuthUI.IdpConfig.FacebookBuilder().build()

            );


            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setLogo(R.drawable.icon_app)
                            .setTheme(R.style.AppTheme)
                            .setTosAndPrivacyPolicyUrls("https://www.google.com", "https://www.google.com")
                            .setAvailableProviders(providers).build(),
                    RC_SIGN_IN
            );
        } else {
            Toast.makeText(getContext(), "Already Have account!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    signUpInterface.finishVerification();
                    Toast.makeText(getContext(), "signUp success", Toast.LENGTH_SHORT).show();
                }

            } else {
                System.out.println(response);
                Toast.makeText(getContext(), "Error, Check your Internet Connection" , Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void onStart() {

        new MaterialAlertDialogBuilder(getActivity())
                .setTitle("Warning , Test version !!!")
                .setMessage("For Test purpose use the social authentication with Yello color , Not Phone auth")
                .setPositiveButton("Ok !!", null)
                .setIcon(R.drawable.icon_app)
                .show();
        super.onStart();
    }
}
