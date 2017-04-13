package panch.com.carefulenough.pages.Challenges.DontTouchPage;


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

public class DontTouchPageFragment extends Fragment {

    @BindView(R.id.layout)
    FrameLayout mLayout;

    private static int TIME = 6;
    private PageListener mListener;

    public DontTouchPageFragment() {
        // Required empty public constructor
    }

    public static DontTouchPageFragment newInstance() {
        return new DontTouchPageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_dont_touch_page, container, false);
        ButterKnife.bind(this, w);
        initCondition();
        return w;
    }

    private void initCondition() {
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onPageFailed();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (InGameActivity) context;
        GameManager.setLoseWhenTimeFinishes(false);
        mListener.onPageChanged(TIME - GameManager.getMinusTime());
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }
}
