package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Степаашка on 23.03.2017.
 */

public class Planet extends Star{
    public Planet(int mass, int size, PointF star,int orbit, double velocity/*, double startAngle*/, Paint style) {
        super(mass,size,new Vec2f(star.x+orbit,star.y)/*.rotate(startAngle)*/,style);
        this.velocity=velocity;
        this.star=star;
        this.rel=new Vec2f(orbit,0);
    }

    @Override
    public Vec2f update() {
        rel.rotate(velocity);
        pos=Vec2f.add(rel,star);
        return pos;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(pos.x,pos.y,size,style);
    }

    private PointF star;
    private double velocity;
    private Vec2f rel=new Vec2f();
}
