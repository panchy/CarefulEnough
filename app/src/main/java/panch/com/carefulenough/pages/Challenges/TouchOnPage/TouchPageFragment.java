package panch.com.carefulenough.pages.Challenges.TouchOnPage;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import panch.com.carefulenough.R;
import panch.com.carefulenough.listeners.PageListener;
import panch.com.carefulenough.pages.Challenges.InGameActivity;
import panch.com.carefulenough.services.GameManager;

public class TouchPageFragment extends Fragment {

    @BindView(R.id.layout)
    FrameLayout mLayout;

    private static int TIME = 6;
    private PageListener mListener;

    public TouchPageFragment() {
        // Required empty public constructor
    }

    public static TouchPageFragment newInstance() {
        return new TouchPageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_touch_page, container, false);
        ButterKnife.bind(this, w);
        initCondition();
        return w;
    }

    private void initCondition() {
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onPageSucceeded();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (InGameActivity) context;
        GameManager.setLoseWhenTimeFinishes(true);
        mListener.onPageChanged(TIME - GameManager.getMinusTime());
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }
}
