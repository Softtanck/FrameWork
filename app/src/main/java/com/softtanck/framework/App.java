package com.softtanck.framework;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.softtanck.framework.utils.ScreenUtils;
import com.softtanck.framework.utils.VolleyUtils;

/**
 * @author Tanck
 * @Description TODO 初始化应用程序数据
 * @date Jan 16, 2015 5:50:13 PM
 */
public class App extends Application {

    private static App instance;

    public ImageLoader imageLoader;

    public RequestQueue requestQueue;

    public ImageLoaderConfig imageLoaderConfig;

    public VolleyUtils volleyUtils;


    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        ConValue.ScreenWidth = ScreenUtils.getScreenWidth(this);
        ConValue.ScreenHeight = ScreenUtils.getScreenHeight(this);
    }

    /**
     * 初始化数据
     */
    private void init() {
        requestQueue = Volley.newRequestQueue(instance);//Volley请求队列
        volleyUtils = volleyUtils.init(requestQueue, this);//Volley工具类
        imageLoader = ImageLoader.getInstance();//图片加载器
        imageLoaderConfig = new ImageLoaderConfig(this);
        imageLoader.init(imageLoaderConfig.getConfig());//初始化ImageLoader
        CrashHandler.init(this);//异常博获器
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActivityContainer.finishAll();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // TODO 对map降低引用,低内存下适应.
    }

    // TODO SharedPreferences的相关设置

}
