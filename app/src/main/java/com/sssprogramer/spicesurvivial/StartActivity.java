package com.sssprogramer.spicesurvivial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        seekBar= (SeekBar)findViewById(R.id.seekBar);
    }
    public void onStartGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("G",seekBar.getProgress());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private SeekBar seekBar;
}
