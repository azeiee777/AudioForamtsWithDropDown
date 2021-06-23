package com.azeiee.audiocompression;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class AppAudioManager implements AudioManager.OnAudioFocusChangeListener {
    private MediaPlayer mPlayer;
    private AudioManagerCallback mCallback;
    private AudioManager audioManager;

    public void setResource(Context context, int fileRes) {
        try {
            mPlayer = MediaPlayer.create(context, fileRes);
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) {
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                int mediaVolumeRequired = (int) (0.8 * maxVolume);
                if (currentVolume < mediaVolumeRequired) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mediaVolumeRequired, 0);
                }
            }
            setMediaPlayerCallbacks();
        } catch (Exception e) {
            if (mCallback != null) {
                mCallback.onError(e.getLocalizedMessage());
            }
        }
    }

    private void setMediaPlayerCallbacks() {
        if (mPlayer != null) {
            mPlayer.setOnCompletionListener(mp -> {
                if (mCallback != null) {
                    mCallback.onComplete();
                }
                release();
            });
        }
    }

    public void setAudioCallback(AudioManagerCallback callback) {
        mCallback = callback;
    }

    public void stop() {
        try {
            if (mPlayer != null && mPlayer.isPlaying()) {
                mPlayer.stop();
                mPlayer.prepare();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() {
        if (mPlayer != null) {
            int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // Play
                mPlayer.start();
            }
        }
    }

    public boolean isPlaying() {
        if (mPlayer != null) {
            mPlayer.isPlaying();
        }
        return false;
    }

    public void release() {
        if (mPlayer != null) {
            if (mPlayer.isPlaying()) {
                mPlayer.stop();
            }
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void pause() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
        }
    }

    @Override
    public void onAudioFocusChange(int i) {

    }
}
