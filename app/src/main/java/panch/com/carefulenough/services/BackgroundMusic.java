package panch.com.carefulenough.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import panch.com.carefulenough.R;

public class BackgroundMusic extends Service {

    private static MediaPlayer mp = null;

    public BackgroundMusic() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private static boolean bgMusicPlaying=false;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mp == null && !bgMusicPlaying) {

            mp = MediaPlayer.create(this, R.raw.sneaky);
            mp.setLooping(true);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    if (!mp.isPlaying()) {
                        mp.start();
                        bgMusicPlaying=true;
                    }
                }
            });


        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mp != null && bgMusicPlaying) {
            mp.stop();
            mp.release();
            mp = null;
            bgMusicPlaying=false;
        }
        super.onDestroy();
    }
}
