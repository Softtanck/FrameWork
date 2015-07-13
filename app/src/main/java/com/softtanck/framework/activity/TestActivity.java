package com.softtanck.framework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.softtanck.framework.R;
import com.softtanck.framework.utils.DialogUtils;


public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent=getIntent();
        Toast.makeText(this, intent.getStringExtra("position"), Toast.LENGTH_SHORT).show();
    }

    public void animation(View v){
         DialogUtils.init(TestActivity.this)
                 .createProgressDialog(R.mipmap.progress, "加载中");

   }

   public void animation2(View v){
       DialogUtils.init(TestActivity.this).createSureOrCancelDialog(R.mipmap.ic_launcher,
               "温馨提示", "你是否要退出此应用？", "确定", "再看看");

   }
    public void animation3(View view){
        DialogUtils.init(TestActivity.this).createDownLoadProgressBar(5);
    }
}
