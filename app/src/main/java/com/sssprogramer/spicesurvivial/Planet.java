package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Степаашка on 23.03.2017.
 */

public class Planet extends Star{
    public Planet(float mass, float size, PointF star,float orbit, float velocity/*, double startAngle*/, Paint style) {
        super(mass,size,new Vec2f(star.x+orbit,star.y)/*.rotate(startAngle)*/,style);
        this.velocity=velocity;
        this.star=star;
        this.rel=new Vec2f(orbit,0);
    }

    public Planet(int size, PointF star,int orbit, float velocity/*, double startAngle*/, Paint style) {
        super(size, size,new Vec2f(star.x+orbit,star.y)/*.rotate(startAngle)*/,style);
        this.velocity=velocity;
        this.star=star;
        this.rel=new Vec2f(orbit,0);
    }
    public Planet(int size, PointF star,int orbit, float velocity, float startAngle, Paint style) {
        super(size, size,new Vec2f(star.x+orbit,star.y).rotate(startAngle),style);
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
    private float velocity;
    private Vec2f rel=new Vec2f();
}
