package com.omjoonkim.doyouremember.app.writing;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    public static final String TAG = DatePickerFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month =  calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        Log.v(TAG, year+"/"+month+"/"+day);

        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener)getActivity();

        return new DatePickerDialog(getActivity(), listener, year, month ,day);

    }
}
