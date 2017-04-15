package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;

/**
 * Created by Степаашка on 23.03.2017.
 */

public abstract class GameObject {
    public GameObject(Drawer drawer) {
        this.drawer = drawer;
        this.drawer.setModel(this);
    }

    public abstract Vec2f update();

    public void draw(Canvas canvas){drawer.draw(canvas);}

//      TODO Убрать pos
    public Vec2f pos;
    private Drawer drawer;
    public float mass;
}
