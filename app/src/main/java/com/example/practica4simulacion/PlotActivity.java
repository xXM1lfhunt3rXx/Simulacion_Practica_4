package com.example.practica4simulacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

import J.N;

public class PlotActivity extends AppCompatActivity {

    float speed;
    float angle;
    float height;
    XYPlot plot;
    TextView textViewTiempo;
    TextView textViewDistancia;
    TextView textViewValoresIniciales;
    TextView textViewCACA;
    Button buttonResultados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);
        Intent intent = getIntent();
        height = intent.getFloatExtra("height",1);
        angle = intent.getFloatExtra("angle",0);
        speed = intent.getFloatExtra("speed",0);

        buttonResultados = (Button)findViewById(R.id.buttonResultados);
        textViewTiempo = (TextView)findViewById(R.id.textViewTiempo);
        textViewDistancia = (TextView)findViewById(R.id.textViewDistancia);
        textViewValoresIniciales = (TextView)findViewById(R.id.textViewValoresIniciales);
        textViewCACA = (TextView)findViewById(R.id.textViewCACA);

        textViewValoresIniciales.setText("Velocidad inicial: " + speed + "m/s  Ángulo: " + angle + "°  Altura inicial:" + height + "m.");
        textViewTiempo.setText("Distancia: " + Math.round(distance() * 100.0)/100.0);
        textViewDistancia.setText("Tiempo: " + Math.round(time() * 100.0)/100.0);
        int distanciaTam = (int) Math.ceil(distance());
        int alturaTam = 0;
        int t = 0;
        double j = 0;



        Number[] distancia = new Number[100];
        Number[] altura = new Number[100];
        j = 0;
        int i = 0;
        for(j = 0; j < time(); j = j + time()/distance())
        {
            distancia[i] = xPos(j);
            altura[i] = yPos(j);
            j = time()/distance() + j;
            i = i +1;
        }



        plot = (XYPlot) findViewById(R.id.plot);

        XYSeries series1 = new SimpleXYSeries(Arrays.asList(altura),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Altura");

        LineAndPointFormatter alturaFormat = new LineAndPointFormatter(Color.RED,Color.GREEN,null,null);

        plot.addSeries(series1,alturaFormat);


        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number)obj).floatValue());
                return toAppendTo.append(distancia[i]);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });
        buttonResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PlotActivity.this,ResultadosActivity.class);
                //intent.putExtra("resultadosX",distancia);
                //intent.putExtra("resultadosY",altura);
                //startActivity(intent);

                    textViewCACA.setText(altura[5].toString());


            }
        });
    }

    public double distance(){

        double d = (Math.pow(speed,2) / 9.8) * (Math.sin(angle*Math.PI / 180) + Math.sqrt(Math.pow(Math.sin(angle * Math.PI / 180), 2)
                + (2 * 9.8 * height / Math.pow(speed, 2)))) * Math.cos(angle*Math.PI / 180);
        return d;
    }
    public double time(){
        double t = (speed / 9.8) * (Math.sin(angle * Math.PI / 180) +
                        Math.sqrt(Math.pow(Math.sin(angle * Math.PI / 180),2) + (2 * 9.8 * height) / Math.pow(speed,2)));
        return t;
    }

    public double xPos(double time){
        return  Math.round(0 + ((speed * Math.cos(angle * Math.PI / 180)) * time));
    }

    public double yPos(double time){
        return Math.round(height + (vYT(time) * time) + ((9.8 * Math.pow(time,2)) / 2));
    }
    public double vYT(double time){
        return ((speed * Math.sin(angle * Math.PI / 180)) - (9.8 * time));
    }


}