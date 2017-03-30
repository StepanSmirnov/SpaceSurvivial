package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by Степаашка on 25.03.2017.
 */
//Класс для обработки столкновений
public class Map implements View.OnTouchListener{
    public List<Star> objects=new ArrayList<>();
    public Player player;

    public Map(Rect screen,Rect space,Player player, GameActivity activity) {
        this.screen=screen;
        this.player=player;
        this.space=space;
        this.activity=activity;
        bounds=new Rect(screen.centerX(),screen.centerY(),
                space.right-screen.centerX(),space.bottom-screen.centerY());
        Random rnd=new Random(Calendar.getInstance().get(Calendar.MINUTE));
        for (int i=0;i<STARS_COUNT;i++){
            background.add(new Star(
                    rnd.nextFloat()*MAX_STAR_R,
                    new Vec2f(rnd.nextInt(space.right), rnd.nextInt(space.bottom))
            ));
        }
        this.time=Calendar.getInstance().getTimeInMillis();
    }

    public void render(Canvas canvas) {
//        canvas.save();
        player.update();
        if (!space.contains(player.bounds)){
            player.offset(Vec2f.negative(player.velocity));
            player.velocity.set(0,0);
        }
        if (player.pos.x<bounds.right && player.pos.x>bounds.left)
            offset.set(screen.centerX()-player.pos.x,offset.y);
        if (player.pos.y<bounds.bottom && player.pos.y>bounds.top)
            offset.set(offset.x,screen.centerY()-player.pos.y);
        canvas.translate(offset.x,offset.y);
        for (Star s:background){
                canvas.drawCircle(s.pos.x,s.pos.y,s.size,Styles.getInstance().farStar);
        }
        for (Star obj:objects){
            Vec2f oPos=obj.update();
            obj.draw(canvas);
            if (overlapCircleRectangle(obj,player.bounds)){
//                player.velocity.negate();
//                player.offset(player.velocity);
//                player.velocity.set(0,0);
                //Действия при столкновении
                activity.finish();
            }
            else
                player.addForce(oPos,obj.mass);
        }
        player.draw(canvas);
        canvas.drawText(String.valueOf((Calendar.getInstance().getTimeInMillis()-time)/1000),-offset.x+50,-offset.y+50,Styles.getInstance().score);
//        canvas.drawText(String.valueOf(player.velocity.length()),-offset.x+200,-offset.y+50,Styles.getInstance().score);
    }
    //        Обработка касаний без multiTouch
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_UP)
            player.stop();
        else
            player.moveTo(-offset.x+event.getX(),-offset.y+event.getY());
        return true;
    }
    public static Map BuildMap(Rect screen, Player player, GameActivity activity){
        Map map=new Map(screen,new Rect(0,0,1000,10000),player,activity);

        map.objects.add(new Star(20,25,new Vec2f(350,350),Styles.getInstance().yellow));
        map.objects.add(new Planet(10,10,new PointF(350,350),60,0.2,Styles.getInstance().green));
        map.objects.add(new Planet(20,20,new PointF(350,350),120,0.1,Styles.getInstance().red));
        map.objects.add(new Planet(22,22,new PointF(350,350),170,0.05,Styles.getInstance().green));

        map.objects.add(new Star(20,27,new Vec2f(700,700),Styles.getInstance().red));
        map.objects.add(new Planet(10,10,new PointF(700,700),60,0.2,Styles.getInstance().green));
        map.objects.add(new Planet(20,20,new PointF(700,700),120,0.1,Styles.getInstance().magenta));
        map.objects.add(new Planet(22,22,new PointF(700,700),180,0.05,Styles.getInstance().gray));

        map.objects.add(new Star(0,40,new Vec2f(300,800),Styles.getInstance().yellow));
        map.objects.add(new Star(40,38,new Vec2f(300,800),Styles.getInstance().black));
        return map;
    }
    private boolean overlapCircleRectangle(Star s, Rect r) {
        PointF closest = new PointF(s.pos.x,s.pos.y);
        if(s.pos.x < r.left) {
            closest.x = r.left;
        }
        else if(s.pos.x > r.right) {
            closest.x = r.right;
        }
        if(s.pos.y < r.top) {
            closest.y = r.top;
        }
        else if(s.pos.y > r.bottom) {
            closest.y = r.bottom;
        }
        return Vec2f.lenSquared(s.pos,closest) <= s.size*s.size;
    }
    private Rect screen;
    private Rect bounds;
    private Rect space;
    private GameActivity activity;
    private Vec2f offset=new Vec2f(0,0);
    private List<Star> background=new ArrayList<>(STARS_COUNT);
    private long time=0;
    private static final int STARS_COUNT=500;
    private static final float MAX_STAR_R=2;
};
