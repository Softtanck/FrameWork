package com.softtanck.framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softtanck.framework.R;
import com.softtanck.framework.utils.ScreenUtils;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenUtils.setChenjin(findViewById(R.id.rl), MainActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.rlv);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHoder> {

        @Override
        public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHoder(new TextView(viewGroup.getContext()));
        }

        @Override
        public void onBindViewHolder(MyViewHoder myViewHoder, int i) {
            myViewHoder.textView.setText("我们都是开发者");
        }

        @Override
        public int getItemCount() {
            return 100;
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
