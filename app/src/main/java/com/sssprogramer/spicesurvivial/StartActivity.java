package com.sssprogramer.spicesurvivial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        seekBar= (SeekBar)findViewById(R.id.seekBar);
        game = new Intent(this, GameActivity.class);
    }
    public void onStartGame(View view){

        game.putExtra("G",seekBar.getProgress());
        startActivity(game);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ((TextView) findViewById(R.id.textView)).setText(getIntent().getExtras().getString("message", "Новая игра"));
    }

    private SeekBar seekBar;
    private Intent game;
}
