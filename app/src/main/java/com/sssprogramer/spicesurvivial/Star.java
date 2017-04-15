package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Степаашка on 23.03.2017.
 */

public class Star extends GameObject{
//    TODO Привести в порядок конструкторы
    public Star(float mass, float size, Vec2f center, StarDrawer drawer/*, Paint style*/) {
        super(drawer);
        this.mass=mass;
        this.size=size;
        pos=center;
    }

    public Star(float size, Vec2f center, Drawer drawer){
        super(drawer);
        this.size=size;
        pos=center;
        this.mass=0;
    }

    public Star(float mass, float size, Vec2f center, Paint style){
        super(new StarDrawer(style));
        this.mass=mass;
        this.size=size;
        pos=center;
        this.mass=0;
    }

    @Override
    public Vec2f update() {
        return pos;
    }

//    protected Vec2f pos;
    public float size;
}
