package com.example.projektsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PogodaActivity extends AppCompatActivity {

    Context context;
    TextView textView;
    TextView cityName;
    EditText cityInput;
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pogoda);
        textView = findViewById(R.id.text);
        cityName = findViewById(R.id.name);
        cityInput = findViewById(R.id.city);
        chart = findViewById(R.id.chart1);
        //pobierzDane();
    }

    public void pobierzDane(View view){
        pobierzDane();
    }

    private void pobierzDane() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {



                    try {

                        String city = cityInput.getText().toString();
                        if(!city.isEmpty()){

                        URL geocodingEndpoint = new URL("https://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=1&appid=e077c0a0dce89cc184b0a43e011df395&lang=pl");
                        HttpURLConnection geocodingConnection = (HttpURLConnection) geocodingEndpoint.openConnection();
                        geocodingConnection.setRequestProperty("Accept", "application/json");
                        if (geocodingConnection.getResponseCode() == 200) {
                            InputStreamReader isGeo = new InputStreamReader(geocodingConnection.getInputStream());
                            Gson gson = new Gson();
                            final GeocodingAggregate geocodingAggregate = gson.fromJson(isGeo, GeocodingAggregate[].class)[0];
                            geocodingConnection.disconnect();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cityName.setText(geocodingAggregate.toString());
                                }
                            });

                            URL weatherEndpoint = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + geocodingAggregate.getLat() + "&lon=" + geocodingAggregate.getLon() + "&units=metric&exclude=minutely,daily,alerts&appid=e077c0a0dce89cc184b0a43e011df395&lang=pl");
                            HttpURLConnection weatherConnection = (HttpURLConnection) weatherEndpoint.openConnection();
                            weatherConnection.setRequestProperty("Accept", "application/json");
                            if (weatherConnection.getResponseCode() == 200) {
                                InputStreamReader is = new InputStreamReader(weatherConnection.getInputStream());
                                Gson gson1 = new Gson();
                                final WeatherAggregate weatherAggregate = gson1.fromJson(is, WeatherAggregate.class);
                                weatherConnection.disconnect();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(weatherAggregate.toString());
                                        LineDataSet set1;
                                        ArrayList<Entry> values = new ArrayList<>();
                                        for (int x = 0; x < weatherAggregate.getHourly().length; x++) {

                                            String temp = weatherAggregate.getHourly()[x].getTemp();
                                            values.add(new Entry(x, Float.parseFloat(temp)));
                                        }

                                        set1 = new LineDataSet(values, "Temperatura");
                                        set1.setDrawCircles(false);
                                        set1.setColor(Color.parseColor("#ffaf49"));
                                        set1.setLineWidth(3);
                                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                        dataSets.add(set1);
                                        LineData data = new LineData(dataSets);
                                        chart.setData(data);
                                        chart.invalidate();
                                        Description description = new Description();
                                        description.setText("Czas godzinowo");
                                        chart.setDescription(description);
                                    }
                                });

                            }/* else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, "Wystąpił problem z pobraniem danych", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }*/
                        } /*else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "Wystąpił problem z pobraniem danych", Toast.LENGTH_LONG).show();
                                }
                            });
                        }*/
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                       // runOnUiThread(new Runnable() {
                          //  @Override
                           // public void run() {
                             //  Toast.makeText(getApplicationContext(), "Wystąpił problem z pobraniem danych", Toast.LENGTH_SHORT).show();
                            }
                       // });
                   // }

                }

        });
    }

}