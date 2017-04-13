package panch.com.carefulenough.pages.Raw;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import panch.com.carefulenough.R;

public class RawFragment extends Fragment {


    public RawFragment() {
        // Required empty public constructor
    }

    public static RawFragment newInstance() {
        return new RawFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ButterKnife.bind(this,w);
        return w;
    }

}
