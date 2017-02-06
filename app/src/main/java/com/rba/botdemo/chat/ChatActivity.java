package com.rba.botdemo.chat;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.rba.botdemo.R;
import com.rba.botdemo.base.BaseActivity;
import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.model.entity.MessageEntity;
import com.rba.botdemo.model.response.SynchronizeResponse;

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
        chatAdapter = new ChatAdapter(this, this);
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


        for(SynchronizeResponse.DataBean.PropertyTypeBean propertyTypeBean
                : propertyTypeDB.getPropertyType()){
            objectList.add(new ChatButtonEntity(0, propertyTypeBean.getProperty_id(),
                    propertyTypeBean.getProperty_description()));
        }

        chatAdapter.addData(objectList);


    }

    @Override
    public void onClickChatButton(int pos, ChatButtonEntity chatButtonEntity) {
        Toast.makeText(this, "position "+pos+" - "+chatButtonEntity.getDescription(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imgSend)
    public void onClickSend(){
        String message = txtMessage.getText().toString().trim();
        if(chatPresenter.validMessage(message)){
            objectList.add(new MessageEntity(0, message));
            objectList.add(new MessageEntity(1, message));
            rcvChat.smoothScrollToPosition(objectList.size() - 1);
            chatAdapter.notifyItemInserted(objectList.size()-2);
            chatAdapter.notifyItemInserted(objectList.size()-1);
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
