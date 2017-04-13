package panch.com.carefulenough.pages.Challenges.DontTouchPage;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import panch.com.carefulenough.R;
import panch.com.carefulenough.listeners.PageListener;
import panch.com.carefulenough.pages.Challenges.InGameActivity;
import panch.com.carefulenough.services.GameManager;

public class DontTouchPageFragment extends Fragment {

    @BindView(R.id.layout)
    FrameLayout mLayout;

    @BindView(R.id.sub_layout_1)
    LinearLayout mSubLayout1;

    @BindView(R.id.sub_layout_2)
    LinearLayout mSubLayout2;

    @BindView(R.id.sub_layout_3)
    LinearLayout mSubLayout3;

    private static List<LinearLayout> mLayouts = new ArrayList<>();
    private static int TIME = 5;
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
        initRandomLayout();
        initCondition();
        return w;
    }

    private void initRandomLayout() {
        mLayouts.clear();
        mLayouts.add(mSubLayout1);
        mLayouts.add(mSubLayout2);
        mLayouts.add(mSubLayout3);

        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(mLayouts.size());
        mLayouts.get(index).setVisibility(View.VISIBLE);
    }

    private void initCondition() {
        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
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
