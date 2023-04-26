package com.example.projektsm;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.PieRenderer;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
public class GlosowanieActivity extends AppCompatActivity {
    PieChart pie;
    String s = "wyniki";
    public String link = s+".xml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glosowanie);
        pie = findViewById(R.id.MPC);

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

    private void ProcesPars(XmlPullParser parser) throws IOException, XmlPullParserException{
        ArrayList<Wyniki> wyniki = new ArrayList<>();
        int eventType = parser.getEventType();
        Wyniki current= null;

        while(eventType !=XmlPullParser.END_DOCUMENT){
            String el=null;
            switch(eventType){
                case XmlPullParser.START_TAG:
                    el =parser.getName();

                    if("User".equals(el)){
                        current = new Wyniki();
                        wyniki.add(current);
                    }else if(current !=null){
                        if("Imie".equals(el)){
                            current.Imie=parser.nextText();
                        }else if("Wynik".equals(el)){
                            current.wyn=parser.nextText();
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        int len=wyniki.size();
        int len1=wyniki.size();
        Segment seg_jed = new Segment("Jeden", 30);
        Segment seg_dwa = new Segment("Dwa", 40);
        Segment seg_trz = new Segment("Trzy", 15);
        Segment seg_czt = new Segment("Cztery", 10);
        Segment seg_pie = new Segment("Pięć", 5);

        StringBuilder builder = new StringBuilder();
        for(Wyniki wyniki1 : wyniki){
            builder.append(wyniki1.Imie).append("\n").append(wyniki1.wyn).append("\n\n");

            String num = wyniki1.wyn;

            int numb = Integer.parseInt(num);
            switch(len) {
                case 1:
                    seg_jed = new Segment(wyniki1.Imie+" "+numb,numb);
                    break;
                case 2:
                    seg_dwa = new Segment(wyniki1.Imie+" "+numb,numb);
                    break;
                case 3:
                    seg_trz = new Segment(wyniki1.Imie+" "+numb,numb);
                    break;
                case 4:
                    seg_czt = new Segment(wyniki1.Imie+" "+numb,numb);
                    break;
                case 5:
                    seg_pie = new Segment(wyniki1.Imie+" "+numb,numb);
                    break;
            }
            len--;
        }

        SegmentFormatter formatter_jed = new SegmentFormatter(Color.rgb(10,15,58));
        SegmentFormatter formatter_dwa = new SegmentFormatter(Color.rgb(200,25,28));
        SegmentFormatter formatter_trz = new SegmentFormatter(Color.rgb(120,125,158));
        SegmentFormatter formatter_czt = new SegmentFormatter(Color.rgb(10,155,28));
        SegmentFormatter formatter_pie = new SegmentFormatter(Color.rgb(104,121,21));

        switch(len1) {
            case 1:
                pie.addSegment(seg_jed,formatter_jed);
                formatter_jed.getLabelPaint().setTextSize(50f);
                pie.getRenderer(PieRenderer.class).setStartDegs(90);
                pie.getRenderer(PieRenderer.class).setDonutSize(0.4f,PieRenderer.DonutMode.PERCENT);
                break;
            case 2:
                pie.addSegment(seg_jed,formatter_jed);
                pie.addSegment(seg_dwa,formatter_dwa);
                formatter_jed.getLabelPaint().setTextSize(50f);
                formatter_dwa.getLabelPaint().setTextSize(50f);
                pie.getRenderer(PieRenderer.class).setStartDegs(90);
                pie.getRenderer(PieRenderer.class).setDonutSize(0.4f,PieRenderer.DonutMode.PERCENT);
                break;
            case 3:
                pie.addSegment(seg_jed,formatter_jed);
                pie.addSegment(seg_dwa,formatter_dwa);
                pie.addSegment(seg_trz,formatter_trz);
                formatter_jed.getLabelPaint().setTextSize(50f);
                formatter_dwa.getLabelPaint().setTextSize(50f);
                formatter_trz.getLabelPaint().setTextSize(50f);
                pie.getRenderer(PieRenderer.class).setStartDegs(90);
                pie.getRenderer(PieRenderer.class).setDonutSize(0.4f,PieRenderer.DonutMode.PERCENT);
                break;
            case 4:
                pie.addSegment(seg_jed,formatter_jed);
                pie.addSegment(seg_dwa,formatter_dwa);
                pie.addSegment(seg_trz,formatter_trz);
                pie.addSegment(seg_czt,formatter_czt);
                formatter_jed.getLabelPaint().setTextSize(40f);
                formatter_dwa.getLabelPaint().setTextSize(40f);
                formatter_trz.getLabelPaint().setTextSize(40f);
                formatter_czt.getLabelPaint().setTextSize(40f);
                pie.getRenderer(PieRenderer.class).setStartDegs(90);
                pie.getRenderer(PieRenderer.class).setDonutSize(0.4f,PieRenderer.DonutMode.PERCENT);
                break;
            case 5:
                pie.addSegment(seg_jed,formatter_jed);
                pie.addSegment(seg_dwa,formatter_dwa);
                pie.addSegment(seg_trz,formatter_trz);
                pie.addSegment(seg_czt,formatter_czt);
                pie.addSegment(seg_pie,formatter_pie);
                formatter_jed.getLabelPaint().setTextSize(40f);
                formatter_dwa.getLabelPaint().setTextSize(40f);
                formatter_trz.getLabelPaint().setTextSize(40f);
                formatter_czt.getLabelPaint().setTextSize(40f);
                formatter_pie.getLabelPaint().setTextSize(40f);
                pie.getRenderer(PieRenderer.class).setStartDegs(90);
                pie.getRenderer(PieRenderer.class).setDonutSize(0.4f,PieRenderer.DonutMode.PERCENT);
                break;
        }
    }
}
