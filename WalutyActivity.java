package com.example.projektsm;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WalutyActivity extends AppCompatActivity {

    Button button;
    ProgressBar progressBar;
    ArrayList<Integer> p=new ArrayList<Integer>();
    ListView listView;
    List<Waluta> listaWalut;
    Waluta wybranaWaluta;
    Waluta wybranaWaluta2;
    Context context;
    int global = 0;
    int tmp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waluty);

        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        listView = findViewById(R.id.listView);

        // listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        context = getApplicationContext();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CheckedTextView checkedTextView = (CheckedTextView) view;



                if(checkedTextView.isChecked()){
                    global++;
                    p.add(i);

                }else if(!checkedTextView.isChecked()){
                    global--;
                    p.remove(new Integer(i));

                }
                if(global>=3){
                    listView.setItemChecked(i,false);
                    checkedTextView.setChecked(false);
                    button.setClickable(false);
                    button.setBackgroundColor(Color.parseColor("#BDBDBD"));
                    Toast.makeText(getApplicationContext(),"Tylko dwie waluty!",Toast.LENGTH_LONG).show();

                    global--;
                    p.remove(new Integer(i));
                }else if(global==1||global==2){

                    button.setClickable(true);
                    button.setBackgroundColor(Color.parseColor("#6002ee"));
                    // int x=0,y=0;
                    //x=p.get(0);
                    //y=p.get(1);
                    wybranaWaluta=listaWalut.get(p.get(0));
                    if(p.size()>1){
                        wybranaWaluta2=listaWalut.get(p.get(1));
                    }

                }
            }
        });


        pobierzDane();
    }

    private void pobierzDane() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    //Utworzenie URL
                    URL nbpEndpoint = new URL("https://api.nbp.pl/api/exchangerates/tables/a/?format=json");

                    //Utworzenie połączenia
                    HttpURLConnection nbpConnection = (HttpURLConnection) nbpEndpoint.openConnection();
                    nbpConnection.setRequestProperty("Accept", "application/json");

                    //Sprawdzenie kodu odpowiedzi
                    if (nbpConnection.getResponseCode() == 200) {

                        InputStreamReader is = new InputStreamReader(nbpConnection.getInputStream());
                        Gson gson = new Gson();
                        Waluty[] waluty = gson.fromJson(is,Waluty[].class);

                        listaWalut = waluty[0].getWalutyList();



                    }else{
                        showAlert();
                    }




                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            button.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);

                            ArrayAdapter<Waluta> arrayAdapter = new ArrayAdapter<Waluta>(context, android.R.layout.simple_list_item_checked,listaWalut);



                            listView.setAdapter(arrayAdapter);





                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    showAlert();
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert();
                }

            }
        });
    }

    private void showAlert() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Wystąpił problem z pobraniem danych", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void pokazWykres(View view) {
        if(wybranaWaluta!=null&&wybranaWaluta2!=null) {
            Intent intent = new Intent(this, WykresActivity.class);
            intent.putExtra("waluta",wybranaWaluta);
            intent.putExtra("waluta2",wybranaWaluta2);
            startActivity(intent);

        }else{

            Toast.makeText(getApplicationContext(),"Wybierz 2 waluty", Toast.LENGTH_LONG).show();
        }
    }

}