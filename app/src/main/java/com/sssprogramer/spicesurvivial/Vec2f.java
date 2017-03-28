package com.sssprogramer.spicesurvivial;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by Степаашка on 22.03.2017.
 * Класс вектора со стандартными операциями
 */

public class Vec2f extends PointF {

    public Vec2f() {
    }

    public Vec2f(float x, float y) {
        super(x, y);
    }

    public Vec2f(Point p) {
        super(p);
    }

    public Vec2f(PointF p) {
        super(p.x,p.y);
    }

    public Vec2f mult(float num){//Произведение this и other
        this.x*=num;
        this.y*=num;
        return this;
    }

    public Vec2f div(float num){
        this.x/=num;
        this.y/=num;
        return this;
    }
    public Vec2f add(PointF other){//Сумма this и other
        this.offset(other.x,other.y);
        return this;
    }

    public Vec2f diff(PointF other){//Разность this и other
        this.offset(-other.x,-other.y);
        return this;
    }

    public float lenFrom(PointF other){
        return diff(this, other).length();
    }

    public static float lenSquared(PointF p1, PointF p2){
        PointF len=diff(p1,p2);
        return len.x*len.x+len.y*len.y;
    }

    public Vec2f normalize(){
        return div(this,this.length());
    }

    public Vec2f rotate(double angle){
        double vx = x, vy = y;
        double cosVal = Math.cos(angle),
                sinVal = Math.sin(angle);
        x = (float) (vx * cosVal - vy * sinVal);
        y = (float) (vx * sinVal + vy * cosVal);
        return this;
    }

    public static Vec2f negative(Vec2f vec2f){
        Vec2f tmp=new Vec2f(vec2f);
        tmp.negate();
        return tmp;
    }

    public static Vec2f add(PointF left, PointF right){
        Vec2f res=new Vec2f(left);
        return res.add(right);
    }

    public static Vec2f diff(PointF left, PointF right) {
        Vec2f res=new Vec2f(left);
        return res.diff(right);
    }

    public static Vec2f mult(PointF vec, float num){
        Vec2f res = new Vec2f(vec);
        return res.mult(num);
    }

    public static Vec2f div(PointF vec, float num){
        Vec2f res = new Vec2f(vec);
        return res.div(num);
    }
}
