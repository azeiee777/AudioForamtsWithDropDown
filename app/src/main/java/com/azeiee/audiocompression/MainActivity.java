package com.azeiee.audiocompression;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private MediaPlayer mediaPlayer;
    private Button buttonPlay;
    private Spinner dropdown;
    private static final String[] items = new String[]{"aac", "amr", "flac", "midi", "mp3", "oog", "opus", "wave"};
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setDropdown();
        mediaPlayer = MediaPlayer.create(this,R.raw.welcome_mp3);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                handleButton();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                setResource(R.raw.beep);
                Toast.makeText(this, "aac", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                setResource(R.raw.description_selection_none_impairment_hindi);
                Toast.makeText(this, "amr", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                setResource(R.raw.welcome_aac);
                Toast.makeText(this, "flac", Toast.LENGTH_SHORT).show();
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
        buttonPlay.setText("Play");
    }

    private void setDropdown() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    private void handleButton() {
        if (mediaPlayer.isPlaying() && mediaPlayer != null) {
            mediaPlayer.pause();
            buttonPlay.setText("Play");
        } else {
            mediaPlayer.start();
            buttonPlay.setText("Pause");
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

    private void setResource(int sound) {
        if (mediaPlayer != null) {
            //mediaPlayer.release();
            mediaPlayer.create(this, sound);
        } else {
            mediaPlayer.create(this, sound);
        }

    }

}