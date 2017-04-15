package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Степаашка on 02.04.2017.
 */
public abstract class Map {
    public RectF space;
    public Player player;
    public List<Star> objects = new ArrayList<>();
    public abstract void drawBackground(Canvas canvas);
}
