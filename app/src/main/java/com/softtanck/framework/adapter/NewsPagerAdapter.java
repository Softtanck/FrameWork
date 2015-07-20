package com.softtanck.framework.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/20/2015
 */
public class NewsPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ImageView> mlist;

    public NewsPagerAdapter(Context context, List<ImageView> list) {

        this.context = context;

        this.mlist = list;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position % mlist.size();
        container.removeView(mlist.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mlist.size();
        container.addView(mlist.get(position));
        return mlist.get(position);
    }
}
