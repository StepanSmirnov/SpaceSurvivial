package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;

/**
 * Created by Степаашка on 11.04.2017.
 */

public abstract class Drawer<T extends GameObject>{
    public Drawer(){

    }

    public Drawer(T model) {
        this.model=model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public abstract void draw(Canvas canvas);
    protected T model;
}
