package com.softtanck.framework.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ListView;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.softtanck.framework.adapter.ChatAdapter;
import com.softtanck.framework.R;
import com.softtanck.framework.utils.DialogUtils;
import com.softtanck.framework.utils.LogUtils;

/**
 * @author : Tanck
 * @Description : TODO
 * @date Jan 16, 2015 5:20:57 PM
 */
public class ChatActivity extends BaseActivity {

    private ListView listView;

    private ChatAdapter adapter;

    @Override
    protected int getViewId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void onActivityCreate() {
        listView = (ListView) findViewById(R.id.list);
        adapter = new ChatAdapter(this, "cqm", 1);
        listView.setAdapter(adapter);

        initChat();
    }

    private void initChat() {
        // 注册接收消息广播
        NewMessageBroadcastReceiver receiver = new NewMessageBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(EMChatManager.getInstance().getNewMessageBroadcastAction());
        // 设置广播的优先级别大于Mainacitivity,这样如果消息来的时候正好在chat页面，直接显示消息，而不是提示消息未读
        intentFilter.setPriority(5);
        registerReceiver(receiver, intentFilter);
    }


    /**
     * 消息广播接收者
     */
    private class NewMessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String username = intent.getStringExtra("from");
            String msgid = intent.getStringExtra("msgid");
            // 收到这个广播的时候，message已经在db和内存里了，可以通过id获取mesage对象
            EMMessage message = EMChatManager.getInstance().getMessage(msgid);
            showToast("收到了消息:---->消息类型:" + message.getChatType() + "----消息内容:" + (TextMessageBody) message.getBody());
            LogUtils.d("收到了消息:---->消息类型:" + message.getChatType() + "----消息内容:" + (TextMessageBody) message.getBody());
            adapter.notifyDataSetChanged();
            abortBroadcast();
        }
    }

    public void test(View view) {

        DialogUtils.init(ChatActivity.this).createDownLoadProgressBar(5);

        LogUtils.d("test");
        EMChatManager.getInstance().login("tanck", "422013", new EMCallBack() {
            @Override
            public void onSuccess() {
                showToast("登陆成功");
                LogUtils.d("登陆成功");
                //获取到与聊天人的会话对象。参数username为聊天人的userid或者groupid，后文中的username皆是如此
                EMConversation conversation = EMChatManager.getInstance().getConversation("cqm");
                //创建一条文本消息
                EMMessage message = EMMessage.createSendMessage(EMMessage.Type.TXT);
                //设置消息body
                TextMessageBody txtBody = new TextMessageBody("hello world");
                message.addBody(txtBody);
                //设置接收人
                message.setReceipt("cqm");
                //把消息加入到此会话对象中
                conversation.addMessage(message);
                //发送消息
                EMChatManager.getInstance().sendMessage(message, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("发送成功");
                            }
                        });

                    }

                    @Override
                    public void onError(int i, final String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("失败:"+s);
                            }
                        });
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                LogUtils.d("登陆失败");
            }

            @Override
            public void onProgress(int i, String s) {
                LogUtils.d("登陆中...");
            }
        });
    }
}
