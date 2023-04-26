package com.example.projektsm;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DemografiaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String link;
    public Integer rok;
    ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demografia);

        Spinner spinner =findViewById(R.id.spin);
        Spinner spinner2=findViewById(R.id.spin2);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.lata, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.postcod, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.spin)
        {
            String text = adapterView.getItemAtPosition(i).toString();
            link = text + ".xml";
        }
        else if(adapterView.getId() == R.id.spin2)
        {
            String Ta = adapterView.getItemAtPosition(i).toString();
            try {
                rok = Integer.parseInt(Ta);
            }catch (NumberFormatException ex){

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void sendMessage(View view) {
        ParseXML();
    }

    private void ParseXML() {
        XmlPullParserFactory parserFactory;
        try{
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open(link);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(is,null);
            ProcesPars(parser);
        }catch(XmlPullParserException e){

        }catch (IOException e){

        }
    }

    private void ProcesPars(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<Count> count = new ArrayList<>();
        int eventType = parser.getEventType();
        Count current = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String el = null;
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    el = parser.getName();

                    if ("data".equals(el)) {
                        current = new Count();
                        count.add(current);
                    } else if (current != null) {
                        if ("popyear".equals(el)) {
                            current.rok = parser.nextText();
                        } else if ("url_img".equals(el)) {
                            current.url = parser.nextText();
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        for(Count count1 : count){
            String num = count1.rok;
            String url = count1.url;
            int numb = Integer.parseInt(num);
            if(numb==rok){
                new DownloadImageFromInternet((ImageView) findViewById(R.id.image)).execute(url);
            }
        }

    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView=imageView;
            Toast.makeText(getApplicationContext(), "Może to chwilkę zająć...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}