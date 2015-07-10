package com.softtanck.framework.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.easemob.exceptions.EaseMobException;
import com.softtanck.framework.R;
import com.softtanck.framework.bean.User;
import com.softtanck.framework.ui.PullToRefreshRecycleView;
import com.softtanck.framework.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Tanck
 * @Description : TODO
 * @date Jan 16, 2015 5:20:57 PM
 */
public class TestFragment extends BaseFragment {

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_test;
    }

    @Override
    protected void onAttaching() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
