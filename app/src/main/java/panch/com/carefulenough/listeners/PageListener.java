package panch.com.carefulenough.listeners;

/**
 * Created by Panch on 13.04.2017.
 */

public interface PageListener {
    void onPageChanged(int TimeForThisPage);
    void onPageSucceeded();
    void onPageFailed();
}
