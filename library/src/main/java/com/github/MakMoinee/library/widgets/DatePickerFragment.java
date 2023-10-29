package com.github.MakMoinee.library.widgets;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    DatePickerDialog.OnDateSetListener listener;
    DatePickerDialog dialog;
    long minDate;

    public DatePickerFragment(int minMonth, int minDay, int minYear, DatePickerDialog.OnDateSetListener listener) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(minYear, minMonth, minDay);
        this.minDate = mCalendar.getTimeInMillis();
        this.listener = listener;
    }

    public DatePickerFragment(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
        this.minDate = System.currentTimeMillis();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
        dialog = new DatePickerDialog(getActivity(), listener, year, month, dayOfMonth);
        dialog.getDatePicker().setMinDate(minDate);
        return dialog;
    }
}
