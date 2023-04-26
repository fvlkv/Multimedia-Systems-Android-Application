package com.example.projektsm;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Waluty {

    @Expose(deserialize = false, serialize = false)
    private List<Waluta> walutyList;

    private String no;

    @SerializedName("rates")
    private Waluta[] waluty;

    private String table;

    private String effectiveDate;

    public String getNo ()
    {
        return no;
    }

    public void setNo (String no)
    {
        this.no = no;
    }

    public String getTable ()
    {
        return table;
    }

    public void setTable (String table)
    {
        this.table = table;
    }

    public String getEffectiveDate ()
    {
        return effectiveDate;
    }

    public void setEffectiveDate (String effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }


    public Waluta[] getWaluty() {
        return waluty;
    }

    public void setWaluty(Waluta[] waluty) {
        this.waluty = waluty;
    }

    public List<Waluta> getWalutyList(){
        if(walutyList == null){
            walutyList = Arrays.asList(waluty);
            Collections.sort(walutyList);
        }
        return walutyList;
    }

}