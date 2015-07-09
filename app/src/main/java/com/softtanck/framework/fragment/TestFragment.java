package com.softtanck.framework.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softtanck.framework.R;
import com.softtanck.framework.ui.PullToRefreshRecycleView;
import com.softtanck.framework.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO
 * @date Jan 16, 2015 5:20:57 PM
 */
public class TestFragment extends BaseFragment {

    private PullToRefreshRecycleView recyclerView;

    private List<String> list;

    @Override
    public void onHiddenChanged(boolean hidden) {
        LogUtils.d("data:" + getArguments().getInt("Tanck") + "---->" + hidden);
    }


    @Override
    protected int getLayoutView() {
        return R.layout.fragment_test;
    }

    @Override
    protected void onAttaching() {
        LogUtils.d("onAttaching");
    }


    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.d("onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            LogUtils.d("onResume");
            LogUtils.d("data:" + getArguments().getInt("Tanck") + "---->" + isVisibleToUser);
        } else {
            //相当于Fragment的onPause
            LogUtils.d("onPause");
            LogUtils.d("data:" + getArguments().getInt("Tanck") + "---->" + isVisibleToUser);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("我们都是开发者:" + i);
        }

        recyclerView = (PullToRefreshRecycleView) view.findViewById(R.id.rlv);
        recyclerView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHoder> {

        @Override
        public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHoder(new TextView(viewGroup.getContext()));
        }

        @Override
        public void onBindViewHolder(MyViewHoder myViewHoder, int i) {
            myViewHoder.textView.setText(list.get(i));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyViewHoder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHoder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
