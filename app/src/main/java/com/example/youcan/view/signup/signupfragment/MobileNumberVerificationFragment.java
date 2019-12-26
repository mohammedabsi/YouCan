package com.example.youcan.view.signup.signupfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.youcan.R;
import com.example.youcan.view.signup.SignUpInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.orhanobut.hawk.Hawk;

public class MobileNumberVerificationFragment extends Fragment {
    SignUpInterface signUpInterface;
    PinEntryEditText editTextCode;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    public MobileNumberVerificationFragment() {
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verification_fragment, container, false);
        Button verify = view.findViewById(R.id.verify_btn);
        editTextCode = view.findViewById(R.id.txt_pin_entry);
        progressBar = view.findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySignInCode();
//                progressBar.setVisibility(View.INVISIBLE);


            }
        });
        TextView resend = view.findViewById(R.id.resend_txt);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpInterface.resendMobileNumber();
            }
        });
        return view;
    }

    private void verifySignInCode() {
        progressBar.setVisibility(View.VISIBLE);
        Hawk.init(getContext()).build();
        String codereceived = Hawk.get("codesent");
        String x = editTextCode.getText().toString();
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(codereceived, x);
        signInWithPhoneAuthCredential(phoneAuthCredential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(), "Registered success ....", Toast.LENGTH_SHORT).show();
                            mAuth.getInstance().signOut();


//                            signUpInterface.finishVerification();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(), "Error Code!!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }


}





