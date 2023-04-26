package com.example.projektsm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Waluta implements Comparable<Waluta>, Serializable {

    @SerializedName("code")
    private String kod;

    @SerializedName("mid")
    private double kurs;

    @SerializedName("currency")
    private String nazwa;

    @Expose(deserialize = false, serialize = false)
    private boolean selected = false;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public double getKurs() {
        return kurs;
    }

    public void setKurs(double kurs) {
        this.kurs = kurs;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString()
    {
        return nazwa +" - " +kod +" ("+String.valueOf(kurs)+")";
    }


    @Override
    public int compareTo(Waluta waluta) {
        return this.getNazwa().compareTo(waluta.getNazwa());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}