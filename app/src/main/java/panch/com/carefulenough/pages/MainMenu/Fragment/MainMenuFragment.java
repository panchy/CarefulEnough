package panch.com.carefulenough.pages.MainMenu.Fragment;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.devspark.robototextview.widget.RobotoTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import panch.com.carefulenough.R;
import panch.com.carefulenough.pages.Challenges.InGameActivity;
import panch.com.carefulenough.services.GameManager;


public class MainMenuFragment extends Fragment {

    @BindView(R.id.title)
    RobotoTextView mTitle;

    @BindView(R.id.btn_start)
    FancyButton mBtnStart;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ButterKnife.bind(this, w);
        FX();
        initListeners();
        return w;
    }

    private static MediaPlayer mediaPlayer;

    private void FX() {

        YoYo.with(Techniques.Swing)
                .duration(3000)
                .repeat(1000)
                .playOn(mTitle);


    }

    private void initListeners() {
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                mediaPlayer = null;
                Intent Ingame = new Intent(getActivity(), InGameActivity.class);
                getActivity().startActivity(Ingame);
                getActivity().finish();
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void onStop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.funny_background);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //mediaPlayer.prepareAsync();
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

        }
    }
}
