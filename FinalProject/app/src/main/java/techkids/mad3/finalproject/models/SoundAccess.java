package techkids.mad3.finalproject.models;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import techkids.mad3.finalproject.R;

/**
 * Created by TrungNT on 7/4/2016.
 */
public class SoundAccess {
    private MediaPlayer mediaPlayer;

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
}
