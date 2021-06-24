package com.azeiee.audiocompression;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private MediaPlayer mediaPlayer;
    private Button buttonPlay;
    private Spinner dropdown;
    TextView tvReview;
    int sound;
    private static final String[] items = new String[]{".aac", ".amr", ".flac", ".midi", ".mp3", ".ogg", ".opus", ".wav"};
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setDropdown();
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButton(sound);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                sound = R.raw.welcome_aac;
                releaseMediaPlayer();
                updateReview(R.color.high_size,"39.35mb","Not Supporting our requirement at all");
                setResource(sound);
                break;
            case 1:
                sound = R.raw.welcome_amr;
                releaseMediaPlayer();
                updateReview(R.color.low_size,"13mb","Supporting our requirement perfectly ");
                setResource(sound);
                break;
            case 2:
                sound = R.raw.welcome_flac;
                releaseMediaPlayer();
                updateReview(R.color.high_size,"127.8mb","Not Supporting our requirement at all");
                setResource(sound);
                break;
            case 3:
                sound = R.raw.welcome_mid;
                releaseMediaPlayer();
                updateReview(R.color.high_size,"10.7mb","sound quality is very bad , Not able to recognise");
                setResource(sound);
                break;
            case 4:
                sound = R.raw.welcome_mp3;
                releaseMediaPlayer();
                updateReview(R.color.high_size,"40.65mb","Not Supporting our requirement at all");
                setResource(sound);
                break;
            case 5:
                sound = R.raw.welcome_ogg;
                releaseMediaPlayer();
                updateReview(R.color.average_size,"25.7mb","Not Supporting our requirement perfectly");
                setResource(sound);
                break;
            case 6:
                sound = R.raw.welcome_opus;
                releaseMediaPlayer();
                updateReview(R.color.high_size,"32.85mb","Not Supporting our requirement at all");
                setResource(sound);
                break;
            case 7:
                sound = R.raw.welcome_wav;
                releaseMediaPlayer();
                updateReview(R.color.high_size,"334mb","Not Supporting our requirement at all");
                setResource(sound);
                break;
            default:

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Please select an audio format", Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        buttonPlay = findViewById(R.id.btnPlay);
        dropdown = findViewById(R.id.spinner);
        tvReview = findViewById(R.id.tv_review_box);
        buttonPlay.setText("Play");
    }

    private void setDropdown() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    private void handleButton(int sound) {
        //setResource(sound);
        playSound(sound);
    }

    private void setResource(int sound) {
        if (mediaPlayer != null) {
            releaseMediaPlayer();
        }
        mediaPlayer = MediaPlayer.create(this, sound);
    }

    private void playSound(int sound) {
        if (mediaPlayer == null) {
            setResource(sound);
        }
        if (!mediaPlayer.isPlaying()) {
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

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            //mediaPlayer.release();
            mediaPlayer = null;
        }
        buttonPlay.setText("Play");
    }

    private void updateReview(int colorId, String appSize, String review){
      tvReview.setBackgroundResource(colorId);
      tvReview.setText("App Size: " + appSize +"\n \n" + review );
    }
}