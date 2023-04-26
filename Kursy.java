package com.example.projektsm;

import com.google.gson.annotations.SerializedName;

public class Kursy {

    private String code;

    @SerializedName("rates")
    private Kurs[] kursy;

    private String currency;

    private String table;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Kurs[] getKursy() {
        return kursy;
    }

    public void setKursy(Kurs[] kursy) {
        this.kursy = kursy;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}