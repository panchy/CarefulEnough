package panch.com.carefulenough.pages.Challenges.TouchWhenYouHearTheSound;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import panch.com.carefulenough.R;
import panch.com.carefulenough.listeners.PageListener;
import panch.com.carefulenough.pages.Challenges.InGameActivity;
import panch.com.carefulenough.services.GameManager;

import static android.content.ContentValues.TAG;

public class TouchWhenYouHearTheSoundFragment extends Fragment {

    @BindView(R.id.layout)
    FrameLayout mLayout;

    @BindView(R.id.sub_layout_1)
    LinearLayout mSubLayout1;

    @BindView(R.id.sub_layout_2)
    LinearLayout mSubLayout2;


    private static int TIME = -2;
    private PageListener mListener;

    private static List<Integer> mediaResources = new ArrayList<>();


    public TouchWhenYouHearTheSoundFragment() {
        // Required empty public constructor
    }

    public static TouchWhenYouHearTheSoundFragment newInstance() {
        return new TouchWhenYouHearTheSoundFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_touch_when_you_hear_the_sound, container, false);
        ButterKnife.bind(this, w);
        initLayout();
        return w;
    }


    int i = 0;

    private void initLayout() {
        mSubLayout1.setVisibility(View.VISIBLE);
        SelectedResource = 0;
        mediaResources.clear();
        mediaResources.add(R.raw.a);
        mediaResources.add(R.raw.b);
        mediaResources.add(R.raw.c);
        mediaResources.add(R.raw.d);
        mediaResources.add(R.raw.e);
        mediaResources.add(R.raw.f);
        mediaResources.add(R.raw.g);
        mediaResources.add(R.raw.c_thin);

        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        });
        final Handler handler = new Handler();
        (new Thread() {
            @Override
            public void run() {
                SelectedResource = getRandomRaw();
                for (i = 100; i < 200; i++) {
                    if (i == 199) {
                        playRaw(SelectedResource);
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            mLayout.setBackgroundColor(Color.argb(255, 0, i, 120));
                        }
                    });
                    // next will pause the thread for some time
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ie) {
                        break;
                    }
                }
            }
        }).start();

        initCondition();

    }

    private boolean LOSE_ON_TOUCH = true;
    private static Handler randomVoiceHandler = new Handler();
    Runnable randomVoiceRunnable = new Runnable() {
        @Override
        public void run() {
            if (!LOSE_ON_TOUCH) {
                mListener.onPageFailed();
            }
            mSubLayout1.setVisibility(View.GONE);
            mSubLayout2.setVisibility(View.VISIBLE);
            int random = getRandomRaw();
            playRaw(random);
            if (random == SelectedResource) {
                LOSE_ON_TOUCH = false;
            } else {
                LOSE_ON_TOUCH = true;
            }
            if (randomVoiceHandler == null) {
                randomVoiceHandler = new Handler();
            }

            randomVoiceHandler.postDelayed(randomVoiceRunnable, 1900);
        }
    };

    private void initCondition() {
        LOSE_ON_TOUCH = true;
        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null) {
                    if (LOSE_ON_TOUCH) {
                        mListener.onPageFailed();
                    } else {
                        mListener.onPageSucceeded();
                    }
                }
                mListener = null;
                return true;
            }
        });
        randomVoiceHandler.postDelayed(randomVoiceRunnable, 2500);

    }

    private static int SelectedResource = 0;

    private int getRandomRaw() {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(mediaResources.size());
        return mediaResources.get(index);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (InGameActivity) context;
        GameManager.setLoseWhenTimeFinishes(false);
        GameManager.PLAYED_HEAR_THE_SOUND_ONCE=true;
        mListener.onPageChanged(TIME - GameManager.getMinusTime());
    }

    @Override
    public void onDetach() {
        if (randomVoiceHandler != null) {
            randomVoiceHandler.removeCallbacks(randomVoiceRunnable);
        }
        if (mp != null) {
            mp.release();
            mp = null;
        }
        mListener = null;
        super.onDetach();
    }

    private static MediaPlayer mp = null;

    private void playRaw(int resid) {
        AssetFileDescriptor afd = getActivity().getResources().openRawResourceFd(resid);

        try {
            mp.reset();
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
            mp.prepareAsync();
            afd.close();
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unable to play audio queue do to exception: " + e.getMessage(), e);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Unable to play audio queue do to exception: " + e.getMessage(), e);
        } catch (IOException e) {
            Log.e(TAG, "Unable to play audio queue do to exception: " + e.getMessage(), e);
        }
    }

}
