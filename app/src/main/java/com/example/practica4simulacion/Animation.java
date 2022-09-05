package com.example.practica4simulacion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Animation extends View {
    float x=0;
    float y=200;
    public Animation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint brush= new Paint();
        brush.setStrokeWidth(5);
        brush.setARGB(255,255, 132, 216);
        canvas.drawCircle(x,y,15, brush);

    }

    public void drawCircle(float xx,float yy){
        x=xx;
        y=yy;
        invalidate();
    }

}
