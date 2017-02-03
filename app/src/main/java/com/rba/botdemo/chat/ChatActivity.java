package com.rba.botdemo.chat;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.rba.botdemo.R;
import com.rba.botdemo.base.BaseActivity;
import com.rba.botdemo.model.entity.MessageEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ChatView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rcvChat) RecyclerView rcvChat;
    @BindView(R.id.txtMessage) AppCompatEditText txtMessage;
    private ChatAdapter chatAdapter;
    private ChatPresenter chatPresenter;
    private List<Object> objectList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();

    }

    @Override
    public void init() {

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvChat.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(this);
        rcvChat.setAdapter(chatAdapter);
        rcvChat.setItemAnimator(new DefaultItemAnimator());

        chatPresenter = new ChatPresenter(this);


        for(int i = 0; i < 4; i++){
            MessageEntity messageEntity = new MessageEntity();
            if(i % 2 == 0){
                messageEntity.setType(0);
            }else{
                messageEntity.setType(1);
            }
            messageEntity.setMessage("Message ".concat(String.valueOf(i)));
            objectList.add(messageEntity);
        }

        chatAdapter.addData(objectList);


    }

    @OnClick(R.id.imgSend)
    public void onClickSend(){
        String message = txtMessage.getText().toString().trim();
        if(chatPresenter.validMessage(message)){
            objectList.add(new MessageEntity(0, message));
            objectList.add(new MessageEntity(1, message));
            rcvChat.smoothScrollToPosition(objectList.size() - 1);
            chatAdapter.notifyDataSetChanged();
        }

    }

    /*
    private void load(){
        List<ChatButtonEntity> chatButtonEntityList = new ArrayList<>();

        for(SynchronizeResponse.DataBean.PropertyTypeBean propertyTypeBean
                : propertyTypeDB.getPropertyType()){
            chatButtonEntityList.add(new ChatButtonEntity(propertyTypeBean.getProperty_id(),
                    propertyTypeBean.getProperty_description()));
        }

        chbGeneral.addChatButtons(chatButtonEntityList);
        chbGeneral.setOnChatButtonClickListener(this);

    }
    */

}
