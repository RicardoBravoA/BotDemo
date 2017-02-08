package com.rba.botdemo.component.chatbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.rba.botdemo.R;
import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Bravo on 7/02/17.
 */

public class ChatRadioGroup extends RadioGroup {

    private List<ChatButtonEntity.ChatButtonBean> chatButtonBeanList = new ArrayList<>();
    private LayoutInflater mInflater;
    private ChatButtonOnClick chatButtonOnClick;

    public ChatRadioGroup(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ChatRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context ctx, AttributeSet attrs, int defStyle){
        mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnChatButtonClickListener(ChatButtonOnClick chatButtonOnClick) {
        this.chatButtonOnClick = chatButtonOnClick;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        if (width <= 0){
            return;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton();
    }

    private void drawButton(){

        for(int i = 0; i < chatButtonBeanList.size(); i++){
            final ChatButtonEntity.ChatButtonBean chatButtonBean = chatButtonBeanList.get(i);
            Log.i("z- chatButtonBean", new Gson().toJson(chatButtonBean));

            View view = mInflater.inflate(R.layout.item_chat_radio_button, null);
            AppCompatRadioButton radioButton = (AppCompatRadioButton) view.findViewById(R.id.rbt);
            radioButton.setId(i);
            radioButton.setText(chatButtonBean.getId());

            int value10 = Util.dpToPx(10, getContext());

            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                    Util.dpToPx(200, getContext()), LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(value10, value10, value10, value10);

            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b && chatButtonOnClick != null) {
                        chatButtonOnClick.onChatButtonClick(chatButtonBean);
                    }
                }
            });

            radioButton.setLayoutParams(layoutParams);
            addView(radioButton);

        }

    }

    public void addChatButton(ChatButtonEntity chatButtonEntity) {
        Log.i("z- chatButtonEntity", new Gson().toJson(chatButtonEntity));
        chatButtonBeanList.addAll(chatButtonEntity.getChatButtonBeen());
        drawButton();
    }

}
