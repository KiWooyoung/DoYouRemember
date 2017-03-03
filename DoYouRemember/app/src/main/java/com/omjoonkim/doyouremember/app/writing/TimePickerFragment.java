package com.omjoonkim.doyouremember.app.writing;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment{

    public static final String TAG = TimePickerFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        Log.v(TAG, hour+":"+minute);

        TimePickerDialog.OnTimeSetListener listener = (TimePickerDialog.OnTimeSetListener)getActivity();

        return new TimePickerDialog(getActivity(),listener, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
