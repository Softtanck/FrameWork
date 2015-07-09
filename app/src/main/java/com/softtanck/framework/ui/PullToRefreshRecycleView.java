package com.softtanck.framework.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.softtanck.framework.R;
import com.softtanck.framework.utils.LogUtils;

/**
 * @author : Tanck
 * @Description : TODO
 * @date Jan 16, 2015 5:20:57 PM
 */
public class PullToRefreshRecycleView extends LinearLayout {

    private float oldY;
    private float newY;
    /**
     * 主视图
     */
    private RecyclerView recyclerView;
    private View header;
    private int mHeaderHeight;
    private float rote;
    private boolean isRefresh;
    private boolean mTouch; // false:上拉 true :上拉
    private boolean canIntercept;

    public PullToRefreshRecycleView(Context context) {
        this(context, null);
    }

    public PullToRefreshRecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("NewApi")
    public PullToRefreshRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    /**
     * 初始化布局
     */
    private void initView(Context context) {
        this.setOrientation(VERTICAL);
        header = View.inflate(context, R.layout.header, null);
        addView(header);
        measureView(header);
        mHeaderHeight = header.getMeasuredHeight();
        LogUtils.d("mHeaderHeight:" + mHeaderHeight);
        header.setPadding(0, -mHeaderHeight, 0, 0);
        header.invalidate();
        recyclerView = new RecyclerView(context);
        addView(recyclerView);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                newY = oldY - ev.getY();
                if (0 <= newY) { //上拉
                    mTouch = false;
                } else { //下拉
                    mTouch = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        if (!isRefresh) {
            if (mTouch && 0 == ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition())
                canIntercept = true;
            else
                canIntercept = false;
        } else {
            canIntercept = false;
        }
        return canIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                newY = event.getY() - oldY;
                if (0 < newY) {//下拉
                    rote = newY / 3;
                    header.setPadding(0, -mHeaderHeight + (int) rote, 0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                if ((int) rote > mHeaderHeight / 2) {
                    //是下拉刷新
                    header.setPadding(0, 0, 0, 0);
                    isRefresh = true;
                } else {
                    //不是一半.弹回去
                    header.setPadding(0, -mHeaderHeight, 0, 0);
                    isRefresh = false;
                }
                mTouch = false;
                break;
        }
        return true;
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (null == recyclerView)
            throw new IllegalStateException("this recyclerView not null");
        if (null == recyclerView.getLayoutManager())
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 测量子孩子
     *
     * @param child
     */
    private void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,
                params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
}
