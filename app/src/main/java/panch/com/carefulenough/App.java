package panch.com.carefulenough;

import android.app.Application;
import android.content.Context;

/**
 * Created by Panch on 13.04.2017.
 */

public class App extends Application {

    private static Context mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
