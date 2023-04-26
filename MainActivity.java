package com.example.projektsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    SpeedometerView Speed;
    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText2=findViewById(R.id.editTextNumber2);
        EditText editText=findViewById(R.id.editTextNumber3);
        button1 =(Button) findViewById(R.id.pogoda);
        button2 =(Button) findViewById(R.id.waluty);
        button3 =(Button) findViewById(R.id.demografia);
        button4 =(Button) findViewById(R.id.glosowanie);
        button5 =(Button) findViewById(R.id.otylosc);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPogodaActivity();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWalutyActivity();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDemografiaActivity();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGlosowanieaActivity();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOtyloscActivity();
            }
        });
    }
    public void openPogodaActivity() {
    Intent intent= new Intent(this,PogodaActivity.class);
    startActivity(intent);
    }
    public void openWalutyActivity() {
        Intent intent= new Intent(this,WalutyActivity.class);
        startActivity(intent);
    }
    public void openDemografiaActivity() {
        Intent intent= new Intent(this,DemografiaActivity.class);
        startActivity(intent);
    }
    public void openGlosowanieaActivity() {
        Intent intent= new Intent(this,GlosowanieActivity.class);
        startActivity(intent);
    }
    public void openOtyloscActivity() {
        Intent intent= new Intent(this,GougeActivity.class);
        startActivity(intent);
    }

    public void button12(View view) {
    }
}