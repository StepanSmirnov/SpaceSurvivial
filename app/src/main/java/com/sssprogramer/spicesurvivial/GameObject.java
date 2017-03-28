package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;

/**
 * Created by Степаашка on 23.03.2017.
 */

public abstract class GameObject {
    public abstract Vec2f update();
    public abstract void draw(Canvas canvas);
    public Vec2f pos;
    public float size;
//    public double mass;
}
