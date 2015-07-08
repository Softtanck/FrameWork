package com.softtanck.framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.softtanck.framework.R;
import com.softtanck.framework.utils.ScreenUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenUtils.setChenjin(findViewById(R.id.rl), MainActivity.this);
    }

}
