package com.example.projektsm;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GougeActivity extends AppCompatActivity {

    SpeedometerView Speed;
    private Button button;
    private EditText editText;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otylosc);


    button=findViewById(R.id.button12);
        EditText editText2=findViewById(R.id.editTextNumber2);
        EditText editText=findViewById(R.id.editTextNumber3);
        Speed = (SpeedometerView)findViewById(R.id.speedometer);
        Speed.setLabelConverter(new SpeedometerView.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }

        });

// configure value range and ticks
        Speed.setMaxSpeed(40);

        Speed.setMajorTickStep(10);
        Speed.setMinorTicks(10);

// Configure value range colors
        Speed.addColoredRange(0, 18.5, Color.WHITE);
        Speed.addColoredRange(18.5, 24.9, Color.GREEN);
        Speed.addColoredRange(25, 29.9, Color.CYAN);
        Speed.addColoredRange(30, 40, Color.RED);
        Speed.setSpeed(21.6, 2000, 500);
        //pod zmienna dajemy se se z jasona i git


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText.getText().toString())&&TextUtils.isEmpty(editText2.getText().toString()))
                {
                    Toast.makeText(GougeActivity.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    double waga = Integer.parseInt(editText.getText().toString());
                    double wzrost = Integer.parseInt(editText2.getText().toString());
                    //String temp=editText.getText().toString();
                    double wynik = waga / (wzrost * wzrost);
                    wynik = wynik * 10000;

                    Speed = (SpeedometerView) findViewById(R.id.speedometer);
                    Speed.setLabelConverter(new SpeedometerView.LabelConverter() {
                        @Override
                        public String getLabelFor(double progress, double maxProgress) {
                            return String.valueOf((int) Math.round(progress));
                        }

                    });

// configure value range and ticks
                    Speed.setMaxSpeed(40);
                    Speed.setMajorTickStep(10);
                    Speed.setMinorTicks(10);

// Configure value range colors
                    Speed.addColoredRange(10, 18.5, Color.WHITE);
                    Speed.addColoredRange(18.5, 24.9, Color.GREEN);
                    Speed.addColoredRange(25, 29.9, Color.CYAN);
                    Speed.addColoredRange(30, 40, Color.RED);
                    Speed.setSpeed(wynik, 2000, 500);
                    //pod zmienna dajemy se se z jasona i git


                }
            }
        });
    }


    public void button12(View view) {

    }

}