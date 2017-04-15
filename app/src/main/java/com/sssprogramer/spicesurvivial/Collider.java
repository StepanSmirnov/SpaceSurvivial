package com.sssprogramer.spicesurvivial;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Степаашка on 25.03.2017.
 */
//Класс для обработки столкновений
public class Collider implements View.OnTouchListener{

    public Collider(RectF screen, Map map, GameActivity activity) {
        this.screen=screen;
        this.player=map.player;
        this.objects=map.objects;
        this.activity=activity;
        this.map=map;
        bounds=new RectF(screen.centerX(),screen.centerY(),
                map.space.right-screen.centerX(),map.space.bottom-screen.centerY());

        this.time=Calendar.getInstance().getTimeInMillis();
    }

    public void render(Canvas canvas) {
        canvas.save();
        player.update();
        if (!map.space.contains(player.bounds)){
            if (player.bounds.bottom<map.space.bottom) {
                player.offset(Vec2f.negative(player.velocity));
                player.velocity.set(0, 0);
            }
            else {
                activity.victory();
            }
        }
        if (player.pos.x<bounds.right && player.pos.x>bounds.left)
            offset.set(screen.centerX()-player.pos.x,offset.y);
        if (player.pos.y<bounds.bottom && player.pos.y>bounds.top)
            offset.set(offset.x,screen.centerY()-player.pos.y);
        canvas.translate(offset.x,offset.y);

        map.drawBackground(canvas);
        for (Star obj:objects){
            Vec2f oPos=obj.update();
            obj.draw(canvas);
            if (overlapCircleRectFangle(obj,player.bounds)){
                activity.gameOver();
            }
            else
                player.addForce(oPos,obj.mass);
        }
        player.draw(canvas);
//        canvas.drawText(String.valueOf((Calendar.getInstance().getTimeInMillis()-time)/1000),-offset.x+50,-offset.y+50,Styles.getInstance().score);
//        canvas.drawText(String.valueOf(player.co2),-offset.x+50,-offset.y+50,Styles.getInstance().score);
        canvas.restore();
    }

    public int getCO2(){
        return (int)player.co2;
    }
    //        Обработка касаний без multiTouch
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()!=MotionEvent.ACTION_UP)
            player.moveTo(-offset.x+event.getX(),-offset.y+event.getY());
        return true;
    }
    private static boolean overlapCircleRectFangle(Star s, RectF r) {
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

    private Map map;
    private List<Star> objects;
    private RectF screen;
    private RectF bounds;
    private GameActivity activity;
    private Vec2f offset=new Vec2f(0,0);
    private Player player;
    private long time=0;
}
