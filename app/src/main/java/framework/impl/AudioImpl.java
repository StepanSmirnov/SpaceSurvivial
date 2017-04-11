package framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

import framework.Audio;
import framework.Music;
import framework.Sound;

/**
 * Created by Степаашка on 01.04.2017.
 */

public class AudioImpl implements Audio{
    AssetManager assets;
    SoundPool soundPool;

    public AudioImpl(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new MusicImpl(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Невозможно загрузить музыку '" +
                    filename + "'");
        }
    }
    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new SoundImpl(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Невозможно загрузить звук '" +
                    filename + "'");
        }
    }
}
