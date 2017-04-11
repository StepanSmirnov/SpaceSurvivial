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
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;


public class GameActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Получаем G для отладки
        Player.G = getIntent().getExtras().getInt("G");
        menu = new Intent(this, StartActivity.class);
        loadAssets();

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        Rect screen=new Rect(0,0,size.x,size.y);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        renderView = new FastRenderView(this);
        setContentView(renderView);
        collider = new Collider(screen,new TestMap(),new Player(100,100, hero),this);
        renderView.setOnTouchListener(collider);

    }

    public void gameOver(){
        menu.putExtra("message", "Игра окончена");
        finish();
    }

    public void victory(){
        menu.putExtra("message", "Урорвень пройден");
        finish();
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
    private void loadAssets(){
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        //Загрузка картинок
        AssetManager assetManager=getAssets();
        mediaPlayer=new MediaPlayer();
        try {
            InputStream inputStream =assetManager.open("astronaut_small.png");
            hero= BitmapFactory.decodeStream(inputStream);
            inputStream.close();

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
                collider.render(canvas);
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
    private Collider collider;
    private static final long DELAY=30;
    private SoundPool soundPool;
    private int airSound=-1;
    private MediaPlayer mediaPlayer;
    private Bitmap hero;
    private Intent menu;
}
