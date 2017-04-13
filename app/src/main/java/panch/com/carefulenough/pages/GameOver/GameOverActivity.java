package panch.com.carefulenough.pages.GameOver;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.devspark.robototextview.widget.RobotoTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import panch.com.carefulenough.R;
import panch.com.carefulenough.pages.MainMenu.Activity.MainActivity;
import panch.com.carefulenough.services.GameManager;

public class GameOverActivity extends AppCompatActivity {

    @BindView(R.id.btn_mainmenu)
    FancyButton mBtnMainmenu;

    @BindView(R.id.score)
    RobotoTextView mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ButterKnife.bind(this);
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.fail);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        });
        mBtnMainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(main);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        mScore.setText("Score: " + String.valueOf(GameManager.getScore()));
        GameManager.reset();
    }
}
