package com.example.youcan.view.signup.signupfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youcan.R;
import com.example.youcan.view.signup.SignUpInterface;
import com.hbb20.CountryCodePicker;

public class ResendMobileNumberFragment extends Fragment {
    SignUpInterface signUpInterface;
    CountryCodePicker ccp;
    public ResendMobileNumberFragment() {
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SignUpInterface){
            signUpInterface= (SignUpInterface) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.resend_mobile_number_fragment,container,false);
        final EditText editText=view.findViewById(R.id.editText_carrierNumber2);
        ccp = view.findViewById(R.id.ccp);

        //concat prefix with the number
        ccp.registerCarrierNumberEditText(editText);

        Button continueButton =view.findViewById(R.id.resend_btn);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mobile = ccp.getFormattedFullNumber().trim();
                ccp.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
                    @Override
                    public void onValidityChanged(boolean isValidNumber) {
                        if (!isValidNumber) {
                            editText.setError("Invalid number");
                            return;
                        }
                        signUpInterface.getMobileNumber();


                    }
                });

            }
        });
        return view;
    }
}
