package panch.com.carefulenough.services;

/**
 * Created by Panch on 13.04.2017.
 */

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import panch.com.carefulenough.pages.Challenges.DontTouchPage.DontTouchPageFragment;
import panch.com.carefulenough.pages.Challenges.TouchOnPage.TouchPageFragment;
import panch.com.carefulenough.pages.Challenges.TouchTheColoredShape.TouchTheColoredShapeFragment;
import panch.com.carefulenough.pages.Challenges.TouchWhenYouHearTheSound.TouchWhenYouHearTheSoundFragment;

public class GameManager {

    private static int MinusTime = 0;
    private static int Score = 0;
    private static boolean LOSE_WHEN_TIME_FINISHES = true;

    public static boolean isLoseWhenTimeFinishes() {
        return LOSE_WHEN_TIME_FINISHES;
    }

    public static void setLoseWhenTimeFinishes(boolean loseWhenTimeFinishes) {
        LOSE_WHEN_TIME_FINISHES = loseWhenTimeFinishes;
    }

    public static void reset() {
        MinusTime = 0;
        Score = 0;
        PLAYED_HEAR_THE_SOUND_ONCE=true;
    }

    public static int getMinusTime() {
        return MinusTime;
    }

    public static int getScore() {
        return Score;
    }

    public static void incrementScore() {
        Score += 1;
        incrementMinusTime();
    }

    public static void incrementMinusTime() {
        if (Score == 5)
            MinusTime += 1;
        else if (Score == 10)
            MinusTime += 1;
        else if (Score == 15)
            MinusTime += 1;
        else if (Score == 20)
            MinusTime += 1;


    }

    public static List<Fragment> fragments = new ArrayList<>();

    public static boolean PLAYED_HEAR_THE_SOUND_ONCE=true;
    public static void init() {
        fragments.clear();
        fragments.add(TouchPageFragment.newInstance());
        fragments.add(DontTouchPageFragment.newInstance());
        fragments.add(TouchTheColoredShapeFragment.newInstance());
        if(!PLAYED_HEAR_THE_SOUND_ONCE)
        {
            fragments.add(TouchWhenYouHearTheSoundFragment.newInstance());
        }
        else {
            PLAYED_HEAR_THE_SOUND_ONCE=false;
        }

    }

    private static Random randomGenerator;
    public static Fragment getRandomGameFragment() {
        init();
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(fragments.size());
        return fragments.get(index);
    }

}
