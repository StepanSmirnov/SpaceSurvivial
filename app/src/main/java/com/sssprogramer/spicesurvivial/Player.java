package com.sssprogramer.spicesurvivial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

import java.util.List;

/**
 * Created by Степаашка on 19.03.2017.
 */

public class Player extends GameObject{
    public static int G=1;
    public Vec2f velocity=new Vec2f(0,0);
    public Rect bounds;
    public Player(int x, int y, Bitmap bitmap) {
        this.bitmap=bitmap;
        pos=new Vec2f(x+bitmap.getWidth()/2,y+bitmap.getHeight()/2);
        bounds=new Rect(x,y,x+bitmap.getWidth(),y + bitmap.getHeight());
        this.size=Math.min(bitmap.getWidth(),bitmap.getHeight())/2;
    }
    public void moveTo(float x, float y){
        dir=Vec2f.diff(new PointF(x,y),pos).normalize();
    }
    public void stop(){
        dir.set(0,0);
    }

    public void offset(PointF offset){
        pos.add(offset);
        bounds.offsetTo(((int) pos.x-bitmap.getWidth()/2), ((int) pos.y-bitmap.getHeight()/2));
    }

    @Override
    public Vec2f update(){
        velocity.add(Vec2f.mult(dir,ACCELERATION));
        if (velocity.squared()>MAX_SPEED*MAX_SPEED){
            velocity.normalize().mult(MAX_SPEED);
        }
        offset(velocity);
        return pos;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,bounds.left,bounds.top,null);
        //Для отладки
//        Paint style=new Paint();
//        style.setARGB(255,0,255,0);
//        style.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(bounds,style);
//        canvas.drawCircle(pos.x,pos.y,3,style);
    }

    public void addForce(Vec2f vector, double module){
        //Добавить в расчеты массу планеты и игрока
        Vec2f rel=Vec2f.diff(vector,pos);
        velocity.add(
                Vec2f.normalize(rel).mult((float) (G*module*mass/Math.pow(rel.length(),2)))
        );
    }
    private Vec2f dir=new Vec2f();
    private static final float ACCELERATION= (float) 1.5;
    private static final float MAX_SPEED= (float) 10;
    private static final double mass=1;
    private Bitmap bitmap;
//    private List
}
