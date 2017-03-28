package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.List;

/**
 * Created by Степаашка on 23.03.2017.
 * Класс звездной системы
 */

public class StarSystem {
    public StarSystem(PointF pos) {
        center=pos;
    }
    private final PointF center;
    private Star star;
    private List<Planet> planets;
}
