package com.softtanck.framework.fragment;

import android.os.Bundle;
import android.view.View;

import com.softtanck.framework.R;
import com.softtanck.framework.activity.MainActivity;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/21/2015
 */
public class LearnRecordFragment extends BaseFragment {
    @Override
    protected int getLayoutView() {
        return R.layout.fragment_learn_record;
    }

    @Override
    protected void onAttaching() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        MainActivity.sm.toggle();
    }
}
