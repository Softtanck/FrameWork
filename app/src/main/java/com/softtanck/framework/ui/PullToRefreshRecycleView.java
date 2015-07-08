package com.softtanck.framework.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.softtanck.framework.utils.LogUtils;

/**
 * @author : Tanck
 * @Description : TODO
 * @date Jan 16, 2015 5:20:57 PM
 */
public class PullToRefreshRecycleView extends RecyclerView {

    private float oldY;
    private float newY;

    public PullToRefreshRecycleView(Context context) {
        this(context, null);
    }

    public PullToRefreshRecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        LogUtils.d("postion:"+( (LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition());
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.d("down");
                oldY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
//                LogUtils.d("move");
                newY = e.getY() - oldY;
                if (0 < newY) {//下拉
                    LogUtils.d("下拉");

                } else { //上拉

                }
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.d("up");
                break;
        }
        return super.onTouchEvent(e);
    }
}
