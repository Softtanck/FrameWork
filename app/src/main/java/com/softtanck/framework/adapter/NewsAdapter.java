package com.softtanck.framework.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.softtanck.framework.R;
import com.softtanck.framework.utils.LogUtils;

import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO 新闻适配器
 * @date 7/20/2015
 */
public class NewsAdapter extends BaseAdapter {

    private Context context;

    private List<String> mList;

    public NewsAdapter(Context context, List<String> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.new_list_item, null);
            holder.fl = (FrameLayout) convertView.findViewById(R.id.fl_news_date);
            holder.date = (TextView) convertView.findViewById(R.id.tv_news_date_txt);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_news_head);
            holder.title = (TextView) convertView.findViewById(R.id.tv_news_title);
            holder.descraption = (TextView) convertView.findViewById(R.id.tv_news_decraption);
            holder.button = (Button) convertView.findViewById(R.id.bt_news_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (0 == position) {
            holder.date.setText("2015-07-20");
            holder.fl.setVisibility(View.VISIBLE);
        } else {
            holder.fl.setVisibility(View.GONE);
        }

        holder.iv.setImageResource(R.mipmap.ic_launcher);
        holder.title.setText(mList.get(position));
        holder.descraption.setText("我是描述");
        holder.button.setText("Lenovo新闻");

        return convertView;
    }

    private class ViewHolder {
        FrameLayout fl;
        TextView date;
        ImageView iv;
        TextView title;
        TextView descraption;
        Button button;
    }
}
