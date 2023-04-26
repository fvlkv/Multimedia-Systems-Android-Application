package com.example.projektsm;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter extends ValueFormatter {

    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public String getFormattedValue(float value) {
        return format.format(new Date(new Float(value).longValue()));
    }

}
