package com.softtanck.framework.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.softtanck.framework.R;

/**
 * Created by Administrator on 7/9/2015.
 */
public class DialogUtils {

    private Context context;
    private  Dialog dialog;
    public static DialogUtils init(Context context){
        DialogUtils dialogUtils=new DialogUtils();
        dialogUtils.context=context;
        return dialogUtils;
    }


    /**创建转动的对话框
     * @param icon 图片资源
     * @param alertStr 提示信息
     */
    public  void createProgressDialog(int icon,String alertStr){

        View view= LayoutInflater.from(context).inflate(R.layout.loading_dialog,null);

        LinearLayout layout=(LinearLayout)view.findViewById(R.id.dialog_view);
        //设置提示信息
        TextView textView= (TextView)view.findViewById(R.id.tipTextView);
        textView.setText(alertStr);
        //获取动画效果
        ImageView imageView=(ImageView)view.findViewById(R.id.img);
        imageView.setImageResource(icon);
        Animation imageAnimation= AnimationUtils.loadAnimation(context,R.anim.loading_animation);
        imageView.startAnimation(imageAnimation);

        dialog=new Dialog(context,R.style.loading_dialog);
        dialog.setCancelable(true);
        dialog.setContentView(layout);
        show();
    }

    /**创建确认、取消样式的对话框
     * @param icon 图标
     * @param title 标题
     * @param dialogDetail 对话框内容详情
     * @param sureStr   确认按钮
     * @param cancelStr 取消按钮
     */
    public void createSureOrCancelDialog(int icon, String title, String dialogDetail, final String sureStr, final String cancelStr){

        View v=LayoutInflater.from(context).inflate(R.layout.sure_or_cancel_dialog,null);
        LinearLayout layout=(LinearLayout)v.findViewById(R.id.dialog);
        //图标
        ImageView iconImage=(ImageView)v.findViewById(R.id.titleIcon);
        //标题
        TextView titleTv=(TextView)v.findViewById(R.id.title);
        //对话框内容
        TextView dialogContent=(TextView)v.findViewById(R.id.dialogDetail);
        //确定按钮
        Button sureBtn=(Button)v.findViewById(R.id.sureBtn);
        //取消按钮
        Button cancelBtn=(Button)v.findViewById(R.id.cancelBtn);
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO something sure
                Toast.makeText(context,sureStr,Toast.LENGTH_SHORT).show();
                cancel();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO something cancel
                Toast.makeText(context,cancelStr,Toast.LENGTH_SHORT).show();
                cancel();
            }
        });
        iconImage.setImageResource(icon);
        titleTv.setText(title);
        sureBtn.setText(sureStr);
        cancelBtn.setText(cancelStr);
        dialogContent.setText(dialogDetail);
        dialog=new Dialog(context,R.style.loading_dialog);
        dialog.setCancelable(true);
        dialog.setContentView(layout);
        show();
    }


    public void createDownLoadProgressBar(int incrementProgress){
        View v=LayoutInflater.from(context).inflate(R.layout.down_load_dialog,null);
        LinearLayout layout=(LinearLayout)v.findViewById(R.id.down_dialog_view);
        TextView downLoadPercent=(TextView)v.findViewById(R.id.down_load_percent);
        TextView downLoadFinish=(TextView)v.findViewById(R.id.down_load_finish);
        ProgressBar progressBar=(ProgressBar)v.findViewById(R.id.down_load_processBar);
        //设置进度条的步长
        progressBar.incrementProgressBy(incrementProgress);
        //设置下载进度
        downLoadPercent.setText("10%");
        dialog=new Dialog(context,R.style.down_dialog);
        dialog.setContentView(layout);
        show();

    }


    private class DownLoad extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }




    public void  show(){
        dialog.show();

    }


    public void cancel(){
        if (dialog!=null)
        dialog.dismiss();

    }

}
