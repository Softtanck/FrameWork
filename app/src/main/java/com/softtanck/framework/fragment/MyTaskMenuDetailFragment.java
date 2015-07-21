package com.softtanck.framework.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softtanck.framework.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTaskMenuDetailFragment extends Fragment {


    public MyTaskMenuDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_task_menu_detail, container, false);
    }


}
