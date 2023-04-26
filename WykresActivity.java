package com.example.projektsm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WykresActivity extends AppCompatActivity {

    ArrayList<Entry> dane;
    ArrayList<Entry> dane2;
    LineChart lineChart;
    Kursy kursy;
    Kursy kursy2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waluty2);


        lineChart = findViewById(R.id.lineChart);
        lineChart.getDescription().setEnabled(false);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new DateFormatter());




        Waluta selWaluta = (Waluta) getIntent().getSerializableExtra("waluta");
        Waluta selWaluta2 = (Waluta) getIntent().getSerializableExtra("waluta2");
        pobierzDane(selWaluta,selWaluta2);

    }

    private void pobierzDane(Waluta selWaluta, Waluta selWaluta2) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    //Utworzenie URL
                    URL nbpEndpoint = new URL("https://api.nbp.pl/api/exchangerates/rates/a/"+selWaluta.getKod()+"/last/60/?format=json");

                    //Utworzenie połączenia
                    HttpURLConnection nbpConnection = (HttpURLConnection) nbpEndpoint.openConnection();
                    nbpConnection.setRequestProperty("Accept", "application/json");

                    //Sprawdzenie kodu odpowiedzi
                    if (nbpConnection.getResponseCode() == 200) {

                        InputStreamReader is = new InputStreamReader(nbpConnection.getInputStream());
                        Gson gson = new Gson();
                        kursy = gson.fromJson(is,Kursy.class);




                    }

                    URL nbpEndpoint2 = new URL("https://api.nbp.pl/api/exchangerates/rates/a/"+selWaluta2.getKod()+"/last/60/?format=json");
                    HttpURLConnection nbpConnection2 = (HttpURLConnection) nbpEndpoint2.openConnection();
                    nbpConnection2.setRequestProperty("Accept", "application/json");

                    //Sprawdzenie kodu odpowiedzi
                    if (nbpConnection2.getResponseCode() == 200) {

                        InputStreamReader is2 = new InputStreamReader(nbpConnection2.getInputStream());
                        Gson gson2 = new Gson();
                        kursy2 = gson2.fromJson(is2,Kursy.class);




                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dane = new ArrayList<>();
                            dane2 = new ArrayList<>();
                            for(Kurs kurs:kursy.getKursy()){
                                dane.add(kurs.getEntry());

                            }
                            for(Kurs kurs:kursy2.getKursy()){
                                dane2.add(kurs.getEntry());

                            }


                            LineDataSet set = new LineDataSet(dane,selWaluta.getNazwa());
                            LineDataSet set2 = new LineDataSet(dane2,selWaluta2.getNazwa());
                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            dataSets.add(set);
                            dataSets.add(set2);
                            set2.setColor(Color.parseColor("#6002ee"));
                            LineData lineData = new LineData(dataSets);
                            lineChart.setData(lineData);
                            lineChart.invalidate();

                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}