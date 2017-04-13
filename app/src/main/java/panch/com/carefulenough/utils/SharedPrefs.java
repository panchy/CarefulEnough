package panch.com.carefulenough.utils;

import android.content.Context;
import android.content.SharedPreferences;

import panch.com.carefulenough.App;

/**
 * Created by Panch on 26.03.2017.
 */

public class SharedPrefs {
    private static SharedPreferences sharedPref = null;

    public static SharedPreferences getSettings() {
        if (sharedPref == null) {
            sharedPref = App.getAppContext().getSharedPreferences("pref", Context.MODE_PRIVATE);

        }
        return sharedPref;
    }


    public static void setName(String name) {
        getSettings().edit().putString("name", name).apply();
    }

    public static String getName() {
        return getSettings().getString("name", "");
    }


}
