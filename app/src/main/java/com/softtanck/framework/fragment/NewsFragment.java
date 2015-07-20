package com.softtanck.framework.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.softtanck.framework.R;
import com.softtanck.framework.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/20/2015
 */
public class NewsFragment extends BaseFragment {

    private ListView listView;
    private List<String> list;
    private NewsAdapter adapter;

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
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("我是第:" + i + "个新闻");
        }
        adapter = new NewsAdapter(context, list);
        listView = (ListView) view.findViewById(R.id.lv_news_list);
        listView.setAdapter(adapter);
    }

    private void initTitle() {
        holder.titleView.setTitle("新闻公告");
    }
}
