package com.softtanck.framework;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMChatOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.softtanck.framework.utils.LogUtils;
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
        initChat();
        requestQueue = Volley.newRequestQueue(instance);//Volley请求队列
        volleyUtils = volleyUtils.init(requestQueue, this);//Volley工具类
        imageLoader = ImageLoader.getInstance();//图片加载器
        imageLoaderConfig = new ImageLoaderConfig(this);
        imageLoader.init(imageLoaderConfig.getConfig());//初始化ImageLoader
        CrashHandler.init(this);//异常博获器
    }

    /**
     * 初始化环信
     */
    private void initChat() {
        // 初始化环信SDK,一定要先调用init()
        LogUtils.d("initialize EMChat SDK");
        EMChat.getInstance().init(instance);
        // debugmode设为true后，就能看到sdk打印的log了
        EMChat.getInstance().setDebugMode(true);

        // 获取到EMChatOptions对象
        EMChatOptions options = EMChatManager.getInstance().getChatOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);

        //设置一个connectionlistener监听账户重复登陆
//        EMChatManager.getInstance().addConnectionListener(new MyConnectionListener());

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
