package com.softtanck.framework.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softtanck.framework.R;
import com.softtanck.framework.fragment.TestFragment;
import com.softtanck.framework.ui.PullToRefreshRecycleView;
import com.softtanck.framework.utils.LogUtils;
import com.softtanck.framework.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private Fragment testFragment;

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityCreate() {
        ScreenUtils.setChenjin(findViewById(R.id.rl), MainActivity.this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        testFragment = new TestFragment();
        transaction.add(R.id.fl_home_content, testFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
