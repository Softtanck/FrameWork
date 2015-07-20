package com.softtanck.framework.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.softtanck.framework.R;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/20/2015
 */
public class NewsFragment extends BaseFragment {

    private ListView listView;

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_news;
    }

    @Override
    protected void onAttaching() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initTitle();

        initView(view);
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.lv_news_list);
    }

    private void initTitle() {
        holder.titleView.setTitle("新闻公告");
    }
}
