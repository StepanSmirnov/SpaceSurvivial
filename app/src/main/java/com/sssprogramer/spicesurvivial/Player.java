package com.sssprogramer.spicesurvivial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.util.Size;
import android.util.SizeF;

import java.util.List;

/**
 * Created by Степаашка on 19.03.2017.
 */
//TODO Создаввать как можно меньше объектов
public class Player extends GameObject{
    public static int G=1;
    public Vec2f velocity=new Vec2f(0,0);
    public RectF bounds;
    public Player(int x, int y, PointF size, Drawer drawer) {
        super(drawer);
        this.size=size;
        this.mass=1;
        pos=new Vec2f(x+size.x/2,y+size.y/2);
        bounds=new RectF(x,y,x+size.x,y + size.y);
    }

    public void moveTo(float x, float y){
        if (co2>0) dir.set(x,y);
    }

    public void offset(PointF offset){
        pos.add(offset);
        bounds.offsetTo(pos.x-size.x/2, pos.y-size.y/2);
    }

    @Override
    public Vec2f update(){
        if (dir.length()>0) {
            velocity.add(dir.diff(pos).normalize().mult(ACCELERATION));
            co2-=co2>0?co2_rate:co2;
        }
        if (velocity.squared()>MAX_SPEED_SQ)
            velocity.normalize().mult(MAX_SPEED);
        offset(velocity);
        dir.set(0,0);
        return pos;
    }

    public void addForce(Vec2f vector, double module){
        Vec2f rel=Vec2f.diff(vector,pos);
        velocity.add(
                Vec2f.normalize(rel).mult((float) (G*module*mass/Math.pow(rel.length(),2)))
        );
    }
    private Vec2f dir=new Vec2f();
    public float co2=100;
    private static float co2_rate=0.7f;
    private static final float ACCELERATION=1.3f;
    private static final float MAX_SPEED= 10f;
    private static final float MAX_SPEED_SQ= MAX_SPEED*MAX_SPEED;
    private PointF size;
}
