package com.softtanck.framework.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.softtanck.framework.R;
import com.softtanck.framework.activity.MainActivity;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/20/2015
 */
public class MenuFragment extends BaseFragment {

    private ImageView task;

    private ImageView history;

    private ImageView news;

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void onAttaching() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        task = (ImageView) view.findViewById(R.id.iv_my_task);

        history = (ImageView) view.findViewById(R.id.iv_study_history);

        news = (ImageView) view.findViewById(R.id.iv_news);

        task.setOnClickListener(this);
        history.setOnClickListener(this);
        news.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_my_task: // 我的任务
                changeFragment(R.id.fl_home_content,new MyTaskFragment());
                MainActivity.sm.toggle();
                break;
            case R.id.iv_news: // 新闻公告
                changeFragment(R.id.fl_home_content, new NewsFragment());
                break;
            case R.id.iv_study_history: // 学习历史

                break;
        }
    }
}
