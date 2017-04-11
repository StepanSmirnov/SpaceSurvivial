package com.sssprogramer.spicesurvivial;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Степаашка on 26.03.2017.
 */
public class Styles {
    private static Styles ourInstance = new Styles();

    public static Styles getInstance() {
        return ourInstance;
    }

    private Styles() {
        farStar=fromColor(Color.argb(255,0,255,200));
        green=fromColor(Color.GREEN);
        yellow=fromColor(Color.YELLOW);
        red=fromColor(Color.RED);
        black=fromColor(Color.BLACK);
        magenta=fromColor(Color.MAGENTA);
        gray=fromColor(Color.GRAY);
        score=fromColor(Color.WHITE);
        score.setTextSize(28);
        score.setTextAlign(Paint.Align.CENTER);
    }

    public static Paint fromColor(int color){
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        return paint;
    }

    public Paint farStar;
    public Paint green;
    public Paint yellow;
    public Paint red;
    public Paint black;
    public Paint magenta;
    public Paint gray;
    public Paint score;
}
