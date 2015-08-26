package com.softtanck.framework.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.softtanck.framework.R;
import com.softtanck.framework.activity.MainActivity;
import com.softtanck.framework.activity.NewsDetailActivity;
import com.softtanck.framework.adapter.NewsAdapter;
import com.softtanck.framework.adapter.NewsPagerAdapter;
import com.softtanck.framework.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/20/2015
 */
public class NewsFragment extends BaseFragment implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private List<String> list;
    private NewsAdapter adapter;
    private ViewPager viewPager;
    private AbsListView.LayoutParams params;
    private List<ImageView> ivlist;
    private NewsPagerAdapter pageadapter;
    private int currentIndex;
    private LinearLayout header;
    private ImageView imageView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(++currentItem);
            handler.sendEmptyMessageDelayed(currentItem, 3500);
        }
    };
    private int currentItem;

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_news;
    }

    @Override
    protected void onAttaching() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        MainActivity.sm.toggle();
        initTitle();

        initView(view);
    }

    private void initView(View view) {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("我是第" + i + "个新闻");
        }
        intiViewPager();
        adapter = new NewsAdapter(context, list);
        listView = (ListView) view.findViewById(R.id.lv_news_list);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void intiViewPager() {
        header = (LinearLayout) holder.inflater.inflate(R.layout.news_header, null);
        viewPager = (ViewPager) header.findViewById(R.id.vp_news);
        imageView = (ImageView) header.findViewById(R.id.iv_point);
        ivlist = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.ic_launcher);
            ivlist.add(imageView);
        }
        pageadapter = new NewsPagerAdapter(context, ivlist);
        viewPager.setAdapter(pageadapter);
        currentItem = Integer.MAX_VALUE / 2;
        currentItem = currentItem - ((Integer.MAX_VALUE / 2) % ivlist.size());
        viewPager.setCurrentItem(currentItem);
        viewPager.setOnPageChangeListener(this);
        drawPoint();
        handler.sendEmptyMessageDelayed(currentItem, 3500);
    }

    private void initTitle() {
        holder.titleView.setTitle("新闻公告");
    }


    private void drawPoint() {
        int radius = 10; // 半径
        int spacing = 50; // 点之间间隔
        Bitmap points = Bitmap.createBitmap(radius * 2 + spacing * (ivlist.size() - 1), radius * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(points);
        Paint paint = new Paint();
        paint.setAntiAlias(true); // 设置画笔为无锯齿
        paint.setStyle(Paint.Style.FILL); // 实心
        for (int i = 0; i < ivlist.size(); i++) {
            paint.setColor(Color.GRAY);
            if (currentIndex == i) // 设置选中项为白色
                paint.setColor(Color.WHITE);
            canvas.drawCircle(radius + spacing * i, radius, radius, paint);
        }
        imageView.setImageBitmap(points);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        currentIndex = position % ivlist.size();
        drawPoint();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtils.d("postion:" + position);
        Intent newsDetail = new Intent(context, NewsDetailActivity.class);
        newsDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newsDetail);
    }
}
