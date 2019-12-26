package com.example.youcan.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.youcan.R;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        return new   TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {


                String x = java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT).format(c.getTime());
                //todo : time set by user for his reminder Or notification
                Toast.makeText(getContext(), getString(R.string.time_picked)+x, Toast.LENGTH_SHORT).show();
                getTargetFragment().onActivityResult(
                        getTargetRequestCode(),
                        Activity.RESULT_OK,
                        new Intent().putExtra("hour", i)
                        .putExtra("min" , i1)
                );
            }

        }, hour, minute, DateFormat.is24HourFormat(getContext()));
    }


}
