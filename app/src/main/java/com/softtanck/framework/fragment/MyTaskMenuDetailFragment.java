package com.softtanck.framework.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.softtanck.framework.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTaskMenuDetailFragment extends BaseFragment {


    private List<Map<String, String>> data = new ArrayList();
    private Map<String, String> map;
    private String[] taskArray = {"MISC课程", "联想卓越服务", "选修课", "必修课", "考试", "产品类课程", "选修课程", "备件课程", "基础课程",
            "维修课程", "接待课程", "其他"};
    private ListView listView;

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_my_task_menu_detail;
    }

    @Override
    protected void onAttaching() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.learn_task_all).setOnClickListener(this);
        view.findViewById(R.id.learn_child_course).setOnClickListener(this);
        view.findViewById(R.id.learn_child_exam).setOnClickListener(this);
        view.findViewById(R.id.learn_child_survey).setOnClickListener(this);
        view.findViewById(R.id.learn_child_notice).setOnClickListener(this);
        initListView(view);
    }


    private void initListView(View v) {
        listView = (ListView) v.findViewById(R.id.my_task_detail_list);
        for (int i = 0; i < taskArray.length; i++) {
            map = new HashMap();
            map.put("my_task_detail_item", taskArray[i]);
            data.add(map);
        }
        listView.setAdapter(new SimpleAdapter(context, data, R.layout.fragment_my_task_detail_list, new String[]{"my_task_detail_item"}
                , new int[]{R.id.my_task_detail_item}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                holder.showToast(taskArray[position]);
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.learn_task_all:
                holder.showToast("全部");
                break;
            case R.id.learn_child_course:
                holder.showToast("课程");
                break;
            case R.id.learn_child_exam:
                holder.showToast("考试");
                break;
            case R.id.learn_child_survey:
                holder.showToast("调查");
                break;
            case R.id.learn_child_notice:
                holder.showToast("通知");
                break;
        }


    }
}
