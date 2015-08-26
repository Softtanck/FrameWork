package com.softtanck.framework.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.softtanck.framework.ActivityContainer;
import com.softtanck.framework.App;
import com.softtanck.framework.R;
import com.softtanck.framework.slidingmenu.app.SlidingFragmentActivity;
import com.softtanck.framework.utils.VolleyUtils;
import com.softtanck.framework.view.TitleView;

/**
 * @author Tanck
 * @Description TODO 所有Activity的基类
 * @date Jan 16, 2015 5:20:57 PM
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    /** 标题*/
    public TitleView titleView;
    /** 视图填充器*/
    public LayoutInflater inflater;
    /** Fragment管理*/
    public FragmentManager fragmentManager;
    /** json数据帮助类*/
    public Gson gson;
    /** Volley请求队列*/
    public RequestQueue requestQueue;
    /** 图片加载*/
    public ImageLoader imageLoader;
    /** 上下文*/
    public Context context;
    /** 网络工具*/
    public VolleyUtils volleyUtils;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fragmentManager = getSupportFragmentManager();
        requestQueue = App.getInstance().requestQueue;
        imageLoader = App.getInstance().imageLoader;
        volleyUtils = App.getInstance().volleyUtils;
        context = getApplicationContext();

        gson = new Gson();

        titleView = (TitleView) findViewById(R.id.comm_titleView);

        ActivityContainer.addActivity(this);

        onActivityCreate();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityContainer.finishActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        requestQueue.cancelAll(App.getInstance());
    }

    /**
     * 如果需要点击就直接重写该方法
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 打印一个Toast
     *
     * @param textId
     */
    public void showToast(int textId) {
        showToast(getString(textId));
    }

    /**
     * 打印一个Toast
     *
     * @param text
     */
    public void showToast(String text) {
        View view = inflater.inflate(R.layout.toast_message, null);
        ((TextView) view.findViewById(R.id.tx_message)).setText(text);
        Toast mToast = new Toast(getApplicationContext());
        mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 100);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
        mToast.show();
    }
    /**
     * 设置布局展示的View
     */
    protected abstract int getViewId();

    /**
     * 初始化布局控件
     */
    protected abstract void onActivityCreate();

}
