package com.example.practica4simulacion;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
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

public class EntriesActivity extends AppCompatActivity {
    double height;
    double angle;
    double speed;
    double endDistance;
    double time;
    ImageView ballImage;
    Button bounceBallButton;
    TextView meters;
    TextView timeTotal;
    TextView inputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);
        Intent mainActivity = getIntent();

        height = mainActivity.getDoubleExtra("height",1);
        angle= mainActivity.getDoubleExtra("angle",0);
        speed= mainActivity.getDoubleExtra("speed",1);
        inputs = (TextView)findViewById(R.id.inputs);
        timeTotal=(TextView)findViewById(R.id.timeTotal);
        meters=(TextView)findViewById(R.id.meters);

        inputs.setText("Velocidad: "+speed+" m/s\nÁngulo: "+angle+"°\nAltura:"+height+" m.");
        timeTotal.setText("Distance: "+ distance());
        meters.setText("Time: "+time());

        ballImage=(ImageView) findViewById(R.id.bounceBallImage);
        bounceBallButton =(Button)findViewById(R.id.bounceBallButton);
        ballImage.clearAnimation();
        TranslateAnimation transAnim = new TranslateAnimation(getDisplayWidth()/2, 0, 0,
                getDisplayHeight());
        transAnim.setStartOffset(500);
        transAnim.setDuration(3000);
        transAnim.setFillAfter(true);
        transAnim.setInterpolator(new BounceInterpolator());
        bounceBallButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ballImage.clearAnimation();

                TranslateAnimation transAnim = new TranslateAnimation(getDisplayWidth()/2, getDisplayWidth(), getDisplayHeight()/2,
                        getDisplayHeight());
                //transAnim.setStartOffset(500);
                transAnim.setDuration(5000);
                transAnim.setFillAfter(true);
                transAnim.setInterpolator(new BounceInterpolator());
                transAnim.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.i("animation", "Starting button dropdown animation");

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.i("animation",
                                "Ending button dropdown animation. Clearing animation and setting layout");
                        ballImage.clearAnimation();
                        final int left = ballImage.getLeft();
                        final int top = ballImage.getTop();
                        final int right = ballImage.getRight();
                        final int bottom = ballImage.getBottom();
                        ballImage.layout(left, top, right, bottom);

                    }
                });
                ballImage.startAnimation(transAnim);
            }
        });

    }
    public static int getDisplayWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getDisplayHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public double distance(){
        return (double) (Math.pow(speed,2)/9.8) *
                (Math.sin(angle)+
                   Math.sqrt(Math.pow(Math.sin(angle),2) +
                   (2*9.8*height/Math.pow(speed,2)))) *
                Math.cos(angle);
    }

    public double time(){
        return (double) (speed/9.8) *
                (Math.sin(angle)+
                 Math.sqrt(
                     Math.pow(Math.sin(angle),2)+
                     (2*9.8*height/Math.pow(speed,2))
                 )
                );
    }

    public double xPos(double time){
        return (double) (speed * Math.pow(Math.cos(angle),time));
    }

    public double yPos(double time){
        return (double)(height + (speed*Math.sin(angle)) -((9.8*Math.pow(time,2))/8));
    }

}