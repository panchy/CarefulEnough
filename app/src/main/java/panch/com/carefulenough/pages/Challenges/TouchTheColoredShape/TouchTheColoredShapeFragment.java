package panch.com.carefulenough.pages.Challenges.TouchTheColoredShape;


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
import mehdi.sakout.fancybuttons.FancyButton;
import panch.com.carefulenough.R;
import panch.com.carefulenough.listeners.PageListener;
import panch.com.carefulenough.pages.Challenges.InGameActivity;
import panch.com.carefulenough.services.GameManager;

public class TouchTheColoredShapeFragment extends Fragment {

    @BindView(R.id.layout)
    FrameLayout mLayout;

    @BindView(R.id.sub_layout_1)
    LinearLayout mSubLayout1;

    @BindView(R.id.sub_layout_2)
    LinearLayout mSubLayout2;

    @BindView(R.id.sub_layout_3)
    LinearLayout mSubLayout3;

    @BindView(R.id.sub_layout_4)
    LinearLayout mSubLayout4;

    @BindView(R.id.sub_layout_5)
    LinearLayout mSubLayout5;

    @BindView(R.id.sub_layout_6)
    LinearLayout mSubLayout6;


    @BindView(R.id.btn_correct_1)
    FancyButton mBtnCorrect1;
    @BindView(R.id.btn_mistake_1)
    FancyButton mBtnMistake1;
    @BindView(R.id.btn_correct_2)
    FancyButton mBtnCorrect2;
    @BindView(R.id.btn_mistake_2)
    FancyButton mBtnMistake2;
    @BindView(R.id.btn_correct_3)
    FancyButton mBtnCorrect3;
    @BindView(R.id.btn_mistake_3)
    FancyButton mBtnMistake3;
    @BindView(R.id.btn_correct_4)
    FancyButton mBtnCorrect4;
    @BindView(R.id.btn_mistake_4)
    FancyButton mBtnMistake4;
    @BindView(R.id.btn_correct_5)
    FancyButton mBtnCorrect5;
    @BindView(R.id.btn_mistake_5)
    FancyButton mBtnMistake5;
    @BindView(R.id.btn_correct_6)
    FancyButton mBtnCorrect6;
    @BindView(R.id.btn_mistake_6)
    FancyButton mBtnMistake6;
    @BindView(R.id.btn_mistake_7)
    FancyButton mBtnMistake7;
    @BindView(R.id.btn_mistake_8)
    FancyButton mBtnMistake8;
    @BindView(R.id.btn_mistake_9)
    FancyButton mBtnMistake9;
    @BindView(R.id.btn_mistake_10)
    FancyButton mBtnMistake10;
    @BindView(R.id.btn_mistake_11)
    FancyButton mBtnMistake11;
    @BindView(R.id.btn_mistake_12)
    FancyButton mBtnMistake12;

    private static int TIME = 6;
    private PageListener mListener;
    private static List<LinearLayout> mLayouts = new ArrayList<>();

    public TouchTheColoredShapeFragment() {
        // Required empty public constructor
    }

    public static TouchTheColoredShapeFragment newInstance() {
        return new TouchTheColoredShapeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_touch_the_colored_shape, container, false);
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
        mLayouts.add(mSubLayout4);
        mLayouts.add(mSubLayout5);
        mLayouts.add(mSubLayout6);

        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(mLayouts.size());
        mLayouts.get(index).setVisibility(View.VISIBLE);


    }

    private void initCondition() {
        mBtnCorrect1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageSucceeded();

                mListener = null;
                return true;
            }
        });
        mBtnCorrect2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageSucceeded();

                mListener = null;
                return true;
            }
        });
        mBtnCorrect3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageSucceeded();

                mListener = null;
                return true;
            }
        });
        mBtnCorrect4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageSucceeded();

                mListener = null;
                return true;
            }
        });
        mBtnCorrect5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageSucceeded();

                mListener = null;
                return true;
            }
        });
        mBtnCorrect6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageSucceeded();

                mListener = null;
                return true;
            }
        });

        //


        mBtnMistake1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });

        mBtnMistake2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });

        mBtnMistake3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });
        mBtnMistake4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });
        mBtnMistake5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });
        mBtnMistake6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });
        mBtnMistake7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });
        mBtnMistake8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });
        mBtnMistake9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });
        mBtnMistake10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });
        mBtnMistake11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mListener != null)
                    mListener.onPageFailed();

                mListener = null;
                return true;
            }
        });
        mBtnMistake12.setOnTouchListener(new View.OnTouchListener() {
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
        GameManager.setLoseWhenTimeFinishes(true);
        mListener.onPageChanged(TIME - GameManager.getMinusTime());
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }
}
