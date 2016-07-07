package techkids.mad3.finalproject.models;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import techkids.mad3.finalproject.R;


/**
 * Created by TrungNT on 7/4/2016.
 */
public class SoundAccess {
    private SoundPool sounds, soundPool;
    private MediaPlayer mediaPlayer;
    int soundCorrect3, soundWrong3, encouragement1, encouragement2, congratulations1;
    private boolean loaded;

    public void loadSoundBackground(Context ctx, String fileName)
    {
        mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor afd = ctx.getAssets().openFd(fileName);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.prepare();
        }catch (Exception e) {
            e.printStackTrace();
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void stopSoundBackground()
    {
        if (mediaPlayer.isPlaying() && mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void initSoundEffects(Context ctx) {
        sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        soundCorrect3 =  sounds.load(ctx, R.raw.dung3, 1);
        soundWrong3 =  sounds.load(ctx, R.raw.sai3, 2);
    }

    public void playCorrectAnswer3(float leftVolume, float rightVolume, int priority, int loop, float rate)
    {
        sounds.play(soundCorrect3, leftVolume, rightVolume, priority, loop, rate);
    }

    public void playWrongAnswer3(float leftVolume, float rightVolume, int priority, int loop, float rate)
    {
        sounds.play(soundWrong3, leftVolume, rightVolume, priority, loop, rate);
    }

    public void playSoundEffectBackground(Context ctx, String fileName)
    {
        mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor afd = ctx.getAssets().openFd(fileName);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.prepare();
        }catch (Exception e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
    }
}
