package com.example.practica4simulacion;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidplot.xy.XYPlot;

public class EntriesActivity extends AppCompatActivity {
    double height;
    double angle;
    String res ="Resultados";
    double speed;
    float[] distancia = new float[100];
    float[] altura = new float[100];
    float[] tiempo = new float[100];
    float origenX;
    int l = 0;
    int i = 0;
    float origenY;
    ImageView ballImage;
    Button bounceBallButton;
    Button buttonRe;
    TextView meters;
    TextView timeTotal;
    TextView inputs;
    TextView textViewPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);
        Intent mainActivity = getIntent();



        height = mainActivity.getDoubleExtra("height",1);
        angle = mainActivity.getDoubleExtra("angle",0);
        speed = mainActivity.getDoubleExtra("speed",1);
        inputs = (TextView)findViewById(R.id.inputs);
        timeTotal = (TextView)findViewById(R.id.timeTotal);
        meters = (TextView)findViewById(R.id.meters);


        inputs.setText("Velocidad: " + speed + "m/s  Ángulo: " + angle + "°  Altura:" + height + "m.");
        timeTotal.setText("Distancia: " + Math.round(distance() * 100.0)/100.0);
        meters.setText("Tiempo: " + + Math.round(time() * 100.0)/100.0);

        ballImage = (ImageView) findViewById(R.id.bounceBallImage);
        bounceBallButton = (Button)findViewById(R.id.bounceBallButton);
        buttonRe = (Button)findViewById(R.id.buttonRe);
        origenX = ballImage.getX();
        origenY = ballImage.getY();

        buttonRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re = new Intent(EntriesActivity.this,ReActivity.class);
                re.putExtra("res",res);

                startActivity(re);

            }
        });




        bounceBallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prepareObjectAnimator(v);

            }
        });
    }

    private void prepareObjectAnimator(final View view) {
        float x = 0;
        float y = 0;
        x = ballImage.getX();
        y = ballImage.getY();
        if(l == 0)
        {
            origenX = x;
            origenY = y;
            l = l + 1;
        }
        double j = 0;

        j = 0;
        for(j = 0; j < time(); j = j + time()/distance())
        {
            distancia[i] = (float) xPos(j);
            altura[i] = (float) yPos(j);
            tiempo[i] = (float) Math.floor(j * 100) / 100;
            //j = time() / distance() + j;
            i = i +1;
        }
        Path path = new Path();

        path.moveTo(origenX + 0,origenY - altura[0] * 10);
        for(int k = 0;k < i; k++)
        {
            path.lineTo(x + distancia[k] * 100,origenY - altura[k] * 100);
            res = res +("(t):"+String.valueOf(tiempo[k])+ " " + "X:"
                    + String.valueOf(distancia[k]) +
                    "," + "Y:"
                    + String.valueOf(altura[k]) +"| ");

        }

        ObjectAnimator animator = ObjectAnimator.ofFloat(ballImage, View.X,View.Y,path);
        animator.setDuration(1000 * i);
        animator.start();

    }

    public static int getDisplayWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getDisplayHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
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
        return  Math.floor((0 + ((speed * Math.cos(angle * Math.PI / 180)) * time))*100)/100;
    }

    public double yPos(double time){
        return Math.floor((height + (vYT(time) * time) + ((9.8 * Math.pow(time,2)) / 2))*100)/100;
    }
    public double vYT(double time){
        return ((speed * Math.sin(angle * Math.PI / 180)) - (9.8 * time));
    }

}