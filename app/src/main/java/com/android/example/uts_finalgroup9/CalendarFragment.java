package com.android.example.uts_finalgroup9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private TextView selectedDateText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        selectedDateText = view.findViewById(R.id.selected_date_text);

        // Default selected date
        long currentDate = calendarView.getDate();
        updateSelectedDateText(currentDate);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            // month is 0-indexed
            String dateStr = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
            selectedDateText.setText("Selected Date: " + dateStr);
        });

        return view;
    }

    private void updateSelectedDateText(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        selectedDateText.setText("Selected Date: " + sdf.format(date));
    }
}
