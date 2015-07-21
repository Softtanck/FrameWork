package com.softtanck.framework.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.view.View;

import com.softtanck.framework.R;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/21/2015
 */
public class EventEditText extends EditText {
    private Drawable searchClearIcon;

    public EventEditText(Context paramContext) {
        super(paramContext);
        setEventListener();
    }

    public EventEditText(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        setEventListener();
    }

    public EventEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        setEventListener();
    }

    private void setEventListener() {
//        this.searchClearIcon = getResources().getDrawable(R.mipmap.edittext_clear_btn);
    }
}
