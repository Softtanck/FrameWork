package com.softtanck.framework;

import android.app.Activity;
import android.content.Context;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.easemob.util.DateUtils;
import com.softtanck.framework.activity.BaseActivity;
import com.softtanck.framework.utils.SmileUtils;

import java.util.Date;

/**
 * @author : Tanck
 * @Description : TODO
 * @date Jan 16, 2015 5:20:57 PM
 */
public class ChatAdapter extends BaseAdapter {

    private final static String TAG = "msg";

    /**
     * 接受文本类型
     */
    private static final int MESSAGE_TYPE_RECV_TXT = 0;
    /**
     * 发送文本类型
     */
    private static final int MESSAGE_TYPE_SENT_TXT = 1;
    /**
     * 发送图片类型
     */
    private static final int MESSAGE_TYPE_SENT_IMAGE = 2;
    /**
     * 发送位置
     */
    private static final int MESSAGE_TYPE_SENT_LOCATION = 3;
    /**
     * 接受位置
     */
    private static final int MESSAGE_TYPE_RECV_LOCATION = 4;
    /**
     * 接受图片
     */
    private static final int MESSAGE_TYPE_RECV_IMAGE = 5;
    /**
     * 发送语音
     */
    private static final int MESSAGE_TYPE_SENT_VOICE = 6;
    /**
     * 接受语音
     */
    private static final int MESSAGE_TYPE_RECV_VOICE = 7;
    /**
     * 发送视频
     */
    private static final int MESSAGE_TYPE_SENT_VIDEO = 8;
    /**
     * 接受视频
     */
    private static final int MESSAGE_TYPE_RECV_VIDEO = 9;

    public static final String IMAGE_DIR = "chat/image/";
    public static final String VOICE_DIR = "chat/audio/";
    public static final String VIDEO_DIR = "chat/video";


    private String username;
    private LayoutInflater inflater;
    private BaseActivity activity;

    // reference to conversation object in chatsdk
    private EMConversation conversation;

    private Context context;

    /**
     * 根据用户名获取对应的聊天记录来适配
     *
     * @param context
     * @param username
     * @param chatType
     */
    public ChatAdapter(Context context, String username, int chatType) {
        this.username = username;
        this.context = context;
        inflater = LayoutInflater.from(context);
        activity = (BaseActivity) context;
        this.conversation = EMChatManager.getInstance().getConversation(username);
    }

    /**
     * 获取Item的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        EMMessage message = conversation.getMessage(position);
        if (message.getType() == EMMessage.Type.TXT) {
            return message.direct == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TXT : MESSAGE_TYPE_SENT_TXT;
        }
        if (message.getType() == EMMessage.Type.IMAGE) {
            return message.direct == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_IMAGE : MESSAGE_TYPE_SENT_IMAGE;
        }
        if (message.getType() == EMMessage.Type.LOCATION) {
            return message.direct == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_LOCATION : MESSAGE_TYPE_SENT_LOCATION;
        }
        if (message.getType() == EMMessage.Type.VOICE) {
            return message.direct == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VOICE : MESSAGE_TYPE_SENT_VOICE;
        }
        if (message.getType() == EMMessage.Type.VIDEO) {
            return message.direct == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VIDEO : MESSAGE_TYPE_SENT_VIDEO;
        }

        return -1;// invalid
    }

    /**
     * 获取Item的类型总数
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 10;//2倍
    }

    /**
     * 根据消息类型创建对应的View
     *
     * @param message
     * @param position
     * @return
     */
    private View createViewByMessage(EMMessage message, int position) {
        switch (message.getType()) {
            case LOCATION:
                return message.direct == EMMessage.Direct.RECEIVE ? inflater.inflate(R.layout.row_received_location, null) : inflater.inflate(
                        R.layout.row_sent_location, null);
            case IMAGE:
                return message.direct == EMMessage.Direct.RECEIVE ? inflater.inflate(R.layout.row_received_picture, null) : inflater.inflate(
                        R.layout.row_sent_picture, null);
            case VOICE:
                return message.direct == EMMessage.Direct.RECEIVE ? inflater.inflate(R.layout.row_received_voice, null) : inflater.inflate(
                        R.layout.row_sent_voice, null);
            case VIDEO:
                return message.direct == EMMessage.Direct.RECEIVE ? inflater.inflate(R.layout.row_received_video, null) : inflater.inflate(
                        R.layout.row_sent_video, null);
            default:
                return message.direct == EMMessage.Direct.RECEIVE ? inflater.inflate(R.layout.row_received_message, null) : inflater.inflate(
                        R.layout.row_sent_message, null);
        }
    }

    @Override
    public int getCount() {
        return conversation.getMsgCount();
    }

    @Override
    public EMMessage getItem(int position) {
        return conversation.getMessage(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final EMMessage message = getItem(position);
        EMMessage.ChatType chatType = message.getChatType();
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = createViewByMessage(message, position);
            if (message.getType() == EMMessage.Type.IMAGE) {
                try {
                    holder.iv = ((ImageView) convertView.findViewById(R.id.iv_sendPicture));
                    holder.head_iv = (ImageView) convertView.findViewById(R.id.iv_userhead);
                    holder.tv = (TextView) convertView.findViewById(R.id.percentage);
                    holder.pb = (ProgressBar) convertView.findViewById(R.id.progressBar);
                    holder.staus_iv = (ImageView) convertView.findViewById(R.id.msg_status);
                    holder.tv_userId = (TextView) convertView.findViewById(R.id.tv_userid);
                } catch (Exception e) {
                }
            } else if (message.getType() == EMMessage.Type.TXT) {
                try {
                    holder.pb = (ProgressBar) convertView.findViewById(R.id.pb_sending);
                    holder.staus_iv = (ImageView) convertView.findViewById(R.id.msg_status);
                    holder.head_iv = (ImageView) convertView.findViewById(R.id.iv_userhead);
                    holder.tv = (TextView) convertView.findViewById(R.id.tv_chatcontent);
                    holder.tv_userId = (TextView) convertView.findViewById(R.id.tv_userid);
                } catch (Exception e) {
                }
            } else if (message.getType() == EMMessage.Type.VOICE) {
                try {
                    holder.iv = ((ImageView) convertView.findViewById(R.id.iv_voice));
                    holder.head_iv = (ImageView) convertView.findViewById(R.id.iv_userhead);
                    holder.tv = (TextView) convertView.findViewById(R.id.tv_length);
                    holder.pb = (ProgressBar) convertView.findViewById(R.id.pb_sending);
                    holder.staus_iv = (ImageView) convertView.findViewById(R.id.msg_status);
                    holder.tv_userId = (TextView) convertView.findViewById(R.id.tv_userid);
                    holder.iv_read_status = (ImageView) convertView.findViewById(R.id.iv_unread_voice);
                } catch (Exception e) {
                }
            } else if (message.getType() == EMMessage.Type.LOCATION) {
                try {
                    holder.head_iv = (ImageView) convertView.findViewById(R.id.iv_userhead);
                    holder.tv = (TextView) convertView.findViewById(R.id.tv_location);
                    holder.pb = (ProgressBar) convertView.findViewById(R.id.pb_sending);
                    holder.staus_iv = (ImageView) convertView.findViewById(R.id.msg_status);
                    holder.tv_userId = (TextView) convertView.findViewById(R.id.tv_userid);
                } catch (Exception e) {
                }
            } else if (message.getType() == EMMessage.Type.VIDEO) {
                try {
                    holder.iv = ((ImageView) convertView.findViewById(R.id.chatting_content_iv));
                    holder.head_iv = (ImageView) convertView.findViewById(R.id.iv_userhead);
                    holder.tv = (TextView) convertView.findViewById(R.id.percentage);
                    holder.pb = (ProgressBar) convertView.findViewById(R.id.progressBar);
                    holder.staus_iv = (ImageView) convertView.findViewById(R.id.msg_status);
                    holder.size = (TextView) convertView.findViewById(R.id.chatting_size_iv);
                    holder.timeLength = (TextView) convertView.findViewById(R.id.chatting_length_iv);
                    holder.playBtn = (ImageView) convertView.findViewById(R.id.chatting_status_btn);
                    holder.container_status_btn = (LinearLayout) convertView.findViewById(R.id.container_status_btn);
                    holder.tv_userId = (TextView) convertView.findViewById(R.id.tv_userid);

                } catch (Exception e) {
                }
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 群聊时，显示接收的消息的发送人的名称
        if (chatType == EMMessage.ChatType.GroupChat && message.direct == EMMessage.Direct.RECEIVE)
            // demo用username代替nick
            holder.tv_userId.setText(message.getFrom());

        // 如果是发送的消息并且不是群聊消息，显示已读textview
        if (message.direct == EMMessage.Direct.SEND && chatType != EMMessage.ChatType.GroupChat) {
            holder.tv_ack = (TextView) convertView.findViewById(R.id.tv_ack);
            if (holder.tv_ack != null) {
                if (message.isAcked) {
                    holder.tv_ack.setVisibility(View.VISIBLE);
                } else {
                    holder.tv_ack.setVisibility(View.INVISIBLE);
                }
            }
        } else {
            // 如果是文本或者地图消息并且不是group messgae，显示的时候给对方发送已读回执
            if ((message.getType() == EMMessage.Type.TXT || message.getType() == EMMessage.Type.LOCATION) && !message.isAcked && chatType != EMMessage.ChatType.GroupChat) {
                try {
                    // 发送已读回执
                    message.isAcked = true;
                    EMChatManager.getInstance().ackMessageRead(message.getFrom(), message.getMsgId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        switch (message.getType()) {
            // 根据消息type显示item
            case IMAGE:
//                handleImageMessage(message, holder, position, convertView);
                break;
            case TXT:
                handleTextMessage(message, holder, position);
                break;
            case LOCATION:
//                handleLocationMessage(message, holder, position, convertView);
                break;
            case VOICE:
//                handleVoiceMessage(message, holder, position, convertView);
                break;
            case VIDEO:
//                handleVideoMessage(message, holder, position, convertView);
                break;
            default:
                // not supported
        }

        if (message.direct == EMMessage.Direct.SEND) {
            View statusView = convertView.findViewById(R.id.msg_status);

            statusView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 显示重发消息的自定义alertdialog
//                    Intent intent = new Intent(activity, AlertDialog.class);
//                    intent.putExtra("msg", activity.getString(R.string.confirm_resend));
//                    intent.putExtra("title", activity.getString(R.string.resend));
//                    intent.putExtra("cancel", true);
//                    intent.putExtra("position", position);
//                    if (message.getType() == EMMessage.Type.TXT)
//                        activity.startActivityForResult(intent, ChatActivity.REQUEST_CODE_TEXT);
//                    else if (message.getType() == EMMessage.Type.VOICE)
//                        activity.startActivityForResult(intent, ChatActivity.REQUEST_CODE_VOICE);
//                    else if (message.getType() == EMMessage.Type.IMAGE)
//                        activity.startActivityForResult(intent, ChatActivity.REQUEST_CODE_PICTURE);
//                    else if (message.getType() == EMMessage.Type.LOCATION)
//                        activity.startActivityForResult(intent, ChatActivity.REQUEST_CODE_LOCATION);
//                    else if (message.getType() == EMMessage.Type.FILE)
//                        activity.startActivityForResult(intent, ChatActivity.REQUEST_CODE_RESEND_NET_DISK);
//                    else if (message.getType() == EMMessage.Type.VIDEO)
//                        activity.startActivityForResult(intent, ChatActivity.REQUEST_CODE_VIDEO);

                }
            });

        }

        TextView timestamp = (TextView) convertView.findViewById(R.id.timestamp);

        if (position == 0) {
            timestamp.setText(DateUtils.getTimestampString(new Date(message.getMsgTime())));
            timestamp.setVisibility(View.VISIBLE);
        } else {
            if (DateUtils.isCloseEnough(message.getMsgTime(), conversation.getMessage(position - 1).getMsgTime())) {
                timestamp.setVisibility(View.GONE);
            } else {
                timestamp.setText(DateUtils.getTimestampString(new Date(message.getMsgTime())));
                timestamp.setVisibility(View.VISIBLE);
            }
        }
        // convertView.setOnClickListener(null);
        return convertView;
    }


    /**
     * 文本消息
     *
     * @param message
     * @param holder
     * @param position
     */
    private void handleTextMessage(EMMessage message, ViewHolder holder, int position) {

        TextMessageBody txtBody = (TextMessageBody) message.getBody();
        Spannable span = SmileUtils.getSmiledText(context, txtBody.getMessage());
        holder.tv.setText(span, TextView.BufferType.SPANNABLE);
//        holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                activity.startActivityForResult(
//                        (new Intent(activity, ContextMenu.class)).putExtra("position", position).putExtra("type",
//                                EMMessage.Type.TXT.ordinal()), ChatActivity.REQUEST_CODE_CONTEXT_MENU);
//                return true;
//            }
//        });
        if (message.direct == EMMessage.Direct.SEND) {
            switch (message.status) {
                case SUCCESS:
                    holder.pb.setVisibility(View.GONE);
                    holder.staus_iv.setVisibility(View.GONE);
                    break;
                case FAIL:
                    holder.pb.setVisibility(View.GONE);
                    holder.staus_iv.setVisibility(View.VISIBLE);
                    break;
                case INPROGRESS:
                    break;
                default:
                    sendMsgInBackground(message, holder);
            }
        }
    }

    /**
     * 发送消息
     *
     * @param message
     * @param holder
     */
    public void sendMsgInBackground(final EMMessage message, final ViewHolder holder) {
        holder.staus_iv.setVisibility(View.GONE);
        holder.pb.setVisibility(View.VISIBLE);
        EMChatManager.getInstance().sendMessage(message, new EMCallBack() {
            @Override
            public void onSuccess() {
                updateSendedView(message, holder);
            }

            @Override
            public void onError(int code, String error) {
                updateSendedView(message, holder);
            }

            @Override
            public void onProgress(int progress, String status) {
            }
        });
    }


    /**
     * 更新ui上消息发送状态
     *
     * @param message
     * @param holder
     */
    private void updateSendedView(final EMMessage message, final ViewHolder holder) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // send success
                if (message.getType() == EMMessage.Type.VIDEO) {
                    holder.tv.setVisibility(View.GONE);
                }
                if (message.status == EMMessage.Status.SUCCESS) {
                    holder.pb.setVisibility(View.GONE);
                    holder.staus_iv.setVisibility(View.GONE);

                } else if (message.status == EMMessage.Status.FAIL) {
                    holder.pb.setVisibility(View.GONE);
                    holder.staus_iv.setVisibility(View.VISIBLE);
                    activity.showToast(R.string.chat_message_fail);
                }
            }
        });
    }

    public static class ViewHolder {
        ImageView iv;
        TextView tv;
        ProgressBar pb;
        ImageView staus_iv;
        ImageView head_iv;
        TextView tv_userId;
        ImageView playBtn;
        TextView timeLength;
        TextView size;
        LinearLayout container_status_btn;
        ImageView iv_read_status;
        TextView tv_ack;
    }
}
