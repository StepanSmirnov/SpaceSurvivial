package framework.impl;

import android.media.SoundPool;

import framework.Sound;

/**
 * Created by Степаашка on 01.04.2017.
 */

public class SoundImpl implements Sound{
    int soundId;
    SoundPool soundPool;
    public SoundImpl(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }
    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }
    @Override
    public void dispose() {
        soundPool.unload(soundId); }
}
