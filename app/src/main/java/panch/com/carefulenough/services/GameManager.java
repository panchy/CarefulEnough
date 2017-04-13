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
        if (Score == 3)
            MinusTime += 1;
        else if (Score == 6)
            MinusTime += 1;
        else if (Score == 8)
            MinusTime += 1;
        else if (Score == 11)
            MinusTime += 1;
    }

    public static List<Fragment> fragments = new ArrayList<>();

    public static void init() {
        fragments.clear();
        fragments.add(DontTouchPageFragment.newInstance());
        fragments.add(TouchPageFragment.newInstance());

    }

    private static Random randomGenerator;

    public static Fragment getRandomGameFragment() {
        init();
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(fragments.size());
        return fragments.get(index);
    }

}
