package cn.food.medicinal.com.yaoshanfang.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.food.medicinal.com.yaoshanfang.R;

/**
 * 品味生活
 */
public class FragmentLife extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_life, null);
        return view;
    }
}
