package com.sssprogramer.spicesurvivial;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;


public class GameActivity extends AppCompatActivity {
    public void destroy(){
        stopService(new Intent(this,GameActivity.class));
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Получаем G для отладки
        Player.G = getIntent().getExtras().getInt("G");

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        Rect screen=new Rect(0,0,size.x,size.y);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        renderView = new FastRenderView(this);
        setContentView(renderView);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        //Загрузка картинок
        AssetManager assetManager=getAssets();
        Bitmap sprite=null;
        mediaPlayer=new MediaPlayer();
        try {
            InputStream inputStream =assetManager.open("astronaut_small.png");
            sprite= BitmapFactory.decodeStream(inputStream);
            inputStream =assetManager.open("game_background.jpg");
            background= BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            assetManager = getAssets();

            AssetFileDescriptor descriptor = assetManager
                    .openFd("air_sound.mp3");
            airSound = soundPool.load(descriptor, 1);

            descriptor = assetManager.openFd("Soundtrack-BoomBoom.mp3");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(), descriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map=Map.BuildMap(screen,new Player(screen.centerX(),screen.centerY(), sprite),this);
        renderView.setOnTouchListener(map);

    }

    protected void onResume() {
        super.onResume();
        renderView.resume();
        mediaPlayer.start();
    }

    protected void onPause() {
        super.onPause();
        renderView.pause();
        mediaPlayer.pause();
    }
    //Класс для прорисовки сцены в отдельном потоке
    class FastRenderView extends SurfaceView implements Runnable {
        public FastRenderView(Context context) {
            super(context);
            holder = getHolder();
        }

        public void resume() {
            running = true;
            renderThread = new Thread(this);
            renderThread.start();
        }

        public void run() {
            while(running) {
                if(!holder.getSurface().isValid())
                    continue;
                Canvas canvas = holder.lockCanvas();
                canvas.drawRGB(0,0,0);
                map.render(canvas);
                holder.unlockCanvasAndPost(canvas);
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        public void pause() {
            running = false;
            try {
                renderThread.join();
            } catch (InterruptedException e) {
            }
        }
        private Thread renderThread = null;
        private SurfaceHolder holder;
        private volatile boolean running = false;
    }
    private FastRenderView renderView;
    private Bitmap background;
    //private Rect screen;
    private Map map;
    private final int GRAVITY_RADIUS=30;
    private static final long DELAY=30;
    private SoundPool soundPool;
    private int airSound=-1;
    private long lastTime=0;
    private MediaPlayer mediaPlayer;
}
