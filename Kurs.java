package com.example.projektsm;

import com.github.mikephil.charting.data.Entry;
import com.google.gson.annotations.Expose;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Kurs {
    private String no;

    private double mid;

    private String effectiveDate;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "mid='" + mid + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                '}';
    }

    @Expose(serialize = false,deserialize = false)
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Entry getEntry() {
        Date date = new Date();
        try {
            date = format.parse(effectiveDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Entry(date.getTime(),(float)getMid());
    }
}