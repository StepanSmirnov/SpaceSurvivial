package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Степаашка on 23.03.2017.
 */

public class Planet extends Star{
//    TODO Привести в порядок конструкторы
    public Planet(float mass, float size, PointF star,float orbit, float velocity, StarDrawer drawer/*, double startAngle, Paint style*/) {
        super(mass,size,new Vec2f(star.x+orbit,star.y), drawer/*.rotate(startAngle),style*/);
        this.velocity=velocity;
        this.star=star;
        this.rel=new Vec2f(orbit,0);
    }

    public Planet(float size, PointF star,int orbit, float velocity, Paint style) {
        super(size, size,new Vec2f(star), new StarDrawer(style)/*.rotate(startAngle),style*/);
        this.velocity=velocity;
        this.star=star;
        this.rel=new Vec2f(orbit,0);
    }
    public Planet(int size, PointF star,int orbit, float velocity, float startAngle, StarDrawer drawer/*, Paint style*/) {
        super(size, size,new Vec2f(star.x+orbit,star.y).rotate(startAngle), drawer/*,style*/);
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

    private PointF star;
    private float velocity;
    private Vec2f rel=new Vec2f();
}
