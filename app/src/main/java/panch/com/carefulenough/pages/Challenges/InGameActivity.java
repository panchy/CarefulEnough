package panch.com.carefulenough.pages.Challenges;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.devspark.robototextview.widget.RobotoTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import panch.com.carefulenough.R;
import panch.com.carefulenough.listeners.PageListener;
import panch.com.carefulenough.pages.Base.BaseActivity;
import panch.com.carefulenough.pages.GameOver.GameOverActivity;
import panch.com.carefulenough.services.BackgroundMusic;
import panch.com.carefulenough.services.GameManager;
import panch.com.carefulenough.utils.FragmentUtils;


public class InGameActivity extends BaseActivity implements PageListener {

    @BindView(R.id.timer)
    RobotoTextView mTimer;
    @BindView(R.id.score)
    RobotoTextView mScore;

    //Timer should be here.
    //Pass-Fail should be here.
    //Timer logic should be here.

    private static MediaPlayer tickTockMp = null;

    private static int TimeLeft = 0;
    private static Handler mHandler;

    @Override
    public void onPageSucceeded() {
        GameManager.incrementScore();
        mScore.setText("Score: "+String.valueOf(GameManager.getScore()));
        FragmentUtils.fragmentToAdd = GameManager.getRandomGameFragment();
        new FragmentUtils(this).setFragment(R.id.container, true);
    }

    @Override
    public void onPageFailed() {
        Intent GameOver = new Intent(InGameActivity.this, GameOverActivity.class);
        startActivity(GameOver);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onPageChanged(int TimeForThisPage) {
        if (mHandler != null) {
            mHandler.removeCallbacks(reduceTime);
        }
        if (mHandler == null)
            mHandler = new Handler();

        TimeLeft = TimeForThisPage;
        mTimer.setText(String.valueOf(TimeLeft));
        mHandler.postDelayed(reduceTime, 1000);
    }

    private Runnable reduceTime = new Runnable() {
        @Override
        public void run() {
            tickTockMp.start();
            mTimer.setText(String.valueOf(TimeLeft));
            TimeLeft -= 1;
            if (TimeLeft == -1 && GameManager.isLoseWhenTimeFinishes()) {
                //Lose Screen
                onPageFailed();
            } else if (TimeLeft == -1 && !GameManager.isLoseWhenTimeFinishes()) {
                //Get a new game
                onPageSucceeded();
            } else {
                mHandler.postDelayed(reduceTime, 1000);
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        ButterKnife.bind(this);
        tickTockMp = MediaPlayer.create(this, R.raw.ticktock);
        tickTockMp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        startService(new Intent(this, BackgroundMusic.class));
        FragmentUtils.fragmentToAdd = GameManager.getRandomGameFragment();
        new FragmentUtils(this).setFragment(R.id.container, false);
    }


    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (tickTockMp != null) {
            tickTockMp.stop();
            tickTockMp.release();
            tickTockMp = null;
        }
        if (mHandler != null) {
            mHandler.removeCallbacks(reduceTime);
        }
        stopService(new Intent(this, BackgroundMusic.class));
        super.onDestroy();
    }
}
