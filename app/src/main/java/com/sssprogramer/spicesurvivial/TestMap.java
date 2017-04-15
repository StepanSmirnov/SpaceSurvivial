package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by Степаашка on 02.04.2017.
 */

public class TestMap extends Map{
    public TestMap(Drawer drawer) {
        space=new RectF(0,0,1000,2000);

        Random rnd=new Random(Calendar.getInstance().get(Calendar.MINUTE));
        for (int i=0;i<STARS_COUNT;i++){
            background.add(new Star(
                    rnd.nextFloat()*MAX_STAR_R,
                    new Vec2f(rnd.nextInt((int)space.right), rnd.nextInt((int)space.bottom)), new StarDrawer(Styles.getInstance().farStar)
            ));
        }

        objects.add(new Star(50,50,new Vec2f(500,500),Styles.getInstance().yellow));
        objects.add(new Planet(20,new PointF(500,500),100,0.1f,Styles.getInstance().green));
        objects.add(new Planet(27,new PointF(500,500),220,0.05f,Styles.getInstance().red));
        objects.add(new Planet(23,new PointF(500,500),310,0.02f,Styles.getInstance().green));
        objects.add(new Planet(30,new PointF(500,500),400,0.01f,Styles.getInstance().green));

        objects.add(new Star(35,35,new Vec2f(800,1000),Styles.getInstance().red));
        objects.add(new Planet(18,new PointF(800,1000),100,0.1f,Styles.getInstance().green));
        objects.add(new Planet(23,new PointF(800,1000),160,0.05f,Styles.getInstance().magenta));

        objects.add(new Star(0,100,new Vec2f(150,900),Styles.getInstance().gray));
        objects.add(new Star(100,98,new Vec2f(150,900),Styles.getInstance().black));

        objects.add(new Star(40,40,new Vec2f(450,1200),Styles.getInstance().red));
        objects.add(new Planet(19,new PointF(450,1200),100,-0.1f,Styles.getInstance().green));
        objects.add(new Planet(25,new PointF(450,1200),160,-0.05f,Styles.getInstance().magenta));
        objects.add(new Planet(27,new PointF(450,1200),220,-0.03f,Styles.getInstance().magenta));

        objects.add(new Star(0,200,new Vec2f(850,1500),Styles.getInstance().gray));
        objects.add(new Star(200,198,new Vec2f(850,1500),Styles.getInstance().black));

        objects.add(new Star(40,40,new Vec2f(250,1700),Styles.getInstance().red));
        objects.add(new Planet(19,new PointF(250,1700),100,0.1f,Styles.getInstance().green));
        objects.add(new Planet(25,new PointF(250,1700),160,0.05f,Styles.getInstance().magenta));
        objects.add(new Planet(27,new PointF(250,1700),220,0.03f,Styles.getInstance().magenta));

        player=new Player(100, 100, new PointF(46,75), drawer);
    }

    @Override
    public void drawBackground(Canvas canvas) {
        for (Star s:background){
            canvas.drawCircle(s.pos.x,s.pos.y,s.size,Styles.getInstance().farStar);
        }
    }
    private List<Star> background=new ArrayList<>(STARS_COUNT);
    private static final int STARS_COUNT=500;
    private static final float MAX_STAR_R=2;
}
