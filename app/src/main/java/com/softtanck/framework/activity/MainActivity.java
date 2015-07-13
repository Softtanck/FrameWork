package com.softtanck.framework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softtanck.framework.R;
import com.softtanck.framework.ui.PullToRefreshRecycleView;
import com.softtanck.framework.utils.LogUtils;
import com.softtanck.framework.utils.ScreenUtils;
import com.softtanck.framework.view.RecycleViewItemCallBack;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private PullToRefreshRecycleView recyclerView;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list =new ArrayList<>();
        for (int i =0;i<100;i++){
            list.add("我们都是开发者:"+i);
        }
        ScreenUtils.setChenjin(findViewById(R.id.rl), MainActivity.this);
        recyclerView = (PullToRefreshRecycleView) findViewById(R.id.rlv);
        MyAdapter myAdapter=new MyAdapter();
        myAdapter.setRecycleViewItemCallBack(new RecycleViewItemCallBack() {
            @Override
            public void onclick(int position) {
                Intent intent=new Intent(MainActivity.this,TestActivity.class);
                intent.putExtra("position",  list.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(myAdapter);

    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHoder> {

        private RecycleViewItemCallBack recycleViewItemCallBack;

        public void setRecycleViewItemCallBack(RecycleViewItemCallBack recycleViewItemCallBack) {
            this.recycleViewItemCallBack = recycleViewItemCallBack;
        }

        @Override
        public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LogUtils.d("---myViewHolder-->"+i);
            return new MyViewHoder(new TextView(viewGroup.getContext()));
        }

        @Override
        public void onBindViewHolder(MyViewHoder myViewHoder, int i) {
            LogUtils.d("---onBindViewHolder-->" + i);
            myViewHoder.textView.setText(list.get(i));
            final int position =i;
            myViewHoder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recycleViewItemCallBack.onclick(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyViewHoder extends RecyclerView.ViewHolder{

        TextView textView;
        public MyViewHoder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
