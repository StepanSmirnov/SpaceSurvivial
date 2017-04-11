package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Степаашка on 02.04.2017.
 */

public abstract class Map {
    public Rect space;
    public PointF spawn;
    public List<Star> objects = new ArrayList<>();
    public abstract void drawBackground(Canvas canvas);
}
