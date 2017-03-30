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
        farStar.setARGB(255,0,255,200);
        farStar.setStyle(Paint.Style.FILL);
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL);
        yellow.setColor(Color.YELLOW);
        yellow.setStyle(Paint.Style.FILL);
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);
        magenta.setColor(Color.MAGENTA);
        magenta.setStyle(Paint.Style.FILL);
        gray.setColor(Color.GRAY);
        gray.setStyle(Paint.Style.FILL);
        score.setColor(Color.WHITE);
        score.setStyle(Paint.Style.FILL);
//        score.setTypeface();
        score.setTextSize(28);
        score.setTextAlign(Paint.Align.CENTER);
    }
    public Paint farStar=new Paint();
    public Paint green=new Paint();
    public Paint yellow=new Paint();
    public Paint red=new Paint();
    public Paint black=new Paint();
    public Paint magenta=new Paint();
    public Paint gray=new Paint();
    public Paint score=new Paint();

}
