package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Степаашка on 23.03.2017.
 */

public class Star extends GameObject{
    public Star(int mass, float size, Vec2f center, Paint style) {
        this.mass=mass;
        this.size=size;
        pos=center;
        this.style=style;
    }

    public Star(float size, Vec2f center){
        this.size=size;
        pos=center;
        this.style=Styles.getInstance().farStar;
    }

    @Override
    public Vec2f update() {
        return pos;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(pos.x,pos.y,size,style);
    }

    public double mass=0;
    protected Paint style;
}
