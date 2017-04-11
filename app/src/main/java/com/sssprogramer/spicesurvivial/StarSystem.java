package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.List;

/**
 * Created by Степаашка on 23.03.2017.
 * Класс звездной системы
 */

public class StarSystem {
    public StarSystem(Star star) {
        this.star=star;
        planets.add(star);
    }
    public void addPlanet(int mass, int size ,int orbit, float velocity/*, double startAngle*/, Paint style){
        planets.add(new Planet(mass, size, star.pos, orbit, velocity, style));
    }
    private Star star;
    private List<Star> planets;
}
