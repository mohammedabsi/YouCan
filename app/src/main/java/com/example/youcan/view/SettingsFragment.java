package com.example.youcan.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.youcan.R;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    Spinner weightspinner, waterspinner, heightspinner;
    ImageView logout_settings;
    TextView show_dialog, textView48;
    public static final int REQUEST_CODE = 11;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        weightspinner = view.findViewById(R.id.weightspinner);
        waterspinner = view.findViewById(R.id.waterspinner);
        heightspinner = view.findViewById(R.id.heightspinner);
        logout_settings = view.findViewById(R.id.logout_settings);
        show_dialog = view.findViewById(R.id.show_dialog);
        textView48 = view.findViewById(R.id.textView48);

        // get fragment manager so we can launch from fragment
        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.setTargetFragment(SettingsFragment.this, REQUEST_CODE);
                timePicker.show(fm, "time picker");
            }
        });


        logout_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });


        ArrayAdapter<CharSequence> weightadapter = ArrayAdapter
                .createFromResource(getContext(), R.array.weightspinner, android.R.layout.simple_spinner_item);
        weightadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightspinner.setAdapter(weightadapter);


        ArrayAdapter<CharSequence> wateradapter = ArrayAdapter
                .createFromResource(getContext(), R.array.waterspinner, android.R.layout.simple_spinner_item);
        wateradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterspinner.setAdapter(wateradapter);

        ArrayAdapter<CharSequence> heightadapter = ArrayAdapter
                .createFromResource(getContext(), R.array.heightspinner, android.R.layout.simple_spinner_item);
        heightadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightspinner.setAdapter(heightadapter);


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check for the results
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
          String  selectedhour = data.getStringExtra("hour");
          String  selectedmin = data.getStringExtra("min");
           System.out.println("hour" +selectedhour +"minute" +selectedmin+"Reached to settingsfragment");

            // set the value of the textview
//            show_dialog.setText(selectedhour+":"+selectedmin+" Daily");
        }
    }

}
