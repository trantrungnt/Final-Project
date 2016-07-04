package techkids.mad3.finalproject.models;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;


/**
 * Created by TrungNT on 7/4/2016.
 */
public class SoundAccess {
    private SoundPool sounds;
    private MediaPlayer mediaPlayer;
    int soundCorrect1;

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

    public void initCorrectAnswer3(Context ctx, int fileName) {
        sounds = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundCorrect1 =  sounds.load(ctx, fileName, 1);
    }

    public void playCorrectAnswer3(float leftVolume, float rightVolume, int priority, int loop, float rate)
    {
        sounds.play(soundCorrect1, leftVolume, rightVolume, priority, loop, rate);
    }

}
