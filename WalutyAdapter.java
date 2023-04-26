package com.example.projektsm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WalutyAdapter extends ArrayAdapter<Waluta> {
    public WalutyAdapter(@NonNull Context context, List<Waluta> data) {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Waluta waluta = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_waluty3, parent, false);
        }

        TextView nazwaTV = convertView.findViewById(R.id.nazwa);
        TextView kursTV = convertView.findViewById(R.id.kurs);
        Switch selectS = convertView.findViewById(R.id.sel);

//        selectS.setOnClickListener(n);

        nazwaTV.setText(waluta.getNazwa()+" ("+waluta.getKod()+")");
        kursTV.setText(String.valueOf(waluta.getKurs()));
        selectS.setChecked(waluta.isSelected());

        return convertView;
    }



}