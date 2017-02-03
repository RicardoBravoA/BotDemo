package com.rba.botdemo.component.chatbutton;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.rba.botdemo.R;
import com.rba.botdemo.model.entity.ChatButtonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class ChatButton extends LinearLayout {

    private List<ChatButtonEntity> chatButtonEntityList = new ArrayList<>();
    private LayoutInflater mInflater;
    private ChatButtonOnClick chatButtonOnClick;

    public ChatButton(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ChatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ChatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context ctx, AttributeSet attrs, int defStyle){
        mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnChatButtonClickListener(ChatButtonOnClick chatButtonOnClick) {
        this.chatButtonOnClick = chatButtonOnClick;
    }

    private void drawButton(){

        for(final ChatButtonEntity chatButtonEntity: chatButtonEntityList){
            View view = mInflater.inflate(R.layout.item_button, null);
            AppCompatButton btn = (AppCompatButton)view.findViewById(R.id.btn);
            btn.setText(chatButtonEntity.getDescription());

            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (chatButtonOnClick != null) {
                        chatButtonOnClick.onChatButtonClick(chatButtonEntity);
                    }
                }
            });
            addView(view);

        }

    }

    public void addChatButton(ChatButtonEntity chatButtonEntity) {
        chatButtonEntityList.add(chatButtonEntity);
        drawButton();
    }

    public void addChatButtons(List<ChatButtonEntity> chatButtonEntityList) {
        this.chatButtonEntityList.addAll(chatButtonEntityList);
        drawButton();
    }


}
