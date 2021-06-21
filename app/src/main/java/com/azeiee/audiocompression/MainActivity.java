package com.azeiee.audiocompression;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button buttonPlay;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mediaPlayer = MediaPlayer.create(this, R.raw.description_selection_english);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked");
                handleButton();
            }
        });
    }

    private void initViews() {
        buttonPlay = findViewById(R.id.btnPlay);
        buttonPlay.setText("Play");
    }

    private void handleButton() {
        Log.d(TAG, "handlePauseButton: pause clicked");
        if (!mediaPlayer.isPlaying() && mediaPlayer != null) {
            mediaPlayer.start();
            buttonPlay.setText("Pause");
        } else {
            mediaPlayer.pause();
            buttonPlay.setText("Play");
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                buttonPlay.setText("Play");
                Toast.makeText(MainActivity.this, "Audio completed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}