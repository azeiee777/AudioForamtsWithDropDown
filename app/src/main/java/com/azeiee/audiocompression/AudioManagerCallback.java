package com.azeiee.audiocompression;

public interface AudioManagerCallback {
    void onComplete();

    void onError(String errorMsg);
}
