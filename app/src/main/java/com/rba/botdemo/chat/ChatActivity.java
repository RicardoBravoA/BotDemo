package com.rba.botdemo.chat;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.rba.botdemo.R;
import com.rba.botdemo.base.BaseActivity;
import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.model.entity.MessageEntity;
import com.rba.botdemo.model.response.ChatResponse;
import com.rba.botdemo.model.response.OperationResponse;
import com.rba.botdemo.util.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ChatView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rcvChat) RecyclerView rcvChat;
    @BindView(R.id.txtMessage) AppCompatEditText txtMessage;
    @BindView(R.id.clGeneral) CoordinatorLayout clGeneral;
    private ChatAdapter chatAdapter;
    private ChatPresenter chatPresenter;
    private List<Object> objectList = new ArrayList<>();
    @Inject
    ChatInteractor chatInteractor;
    private String message = "";
    private String id = "0";
    private String operation_id = "0";
    private String property_id = "0";
    private int response_type_id = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();

    }

    @Override
    public void init() {
        getBotComponent().injectChat(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvChat.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(this, this);
        rcvChat.setAdapter(chatAdapter);
        rcvChat.setItemAnimator(new DefaultItemAnimator());

        chatPresenter = new ChatPresenter(chatInteractor, this);

    }

    @Override
    public void onClickChatButton(ChatButtonEntity chatButtonEntity) {

        if(chatButtonEntity.getType().equals(Constant.TAG_OPERATION)){
            id = chatButtonEntity.getId();
            message = Constant.TAG_OPERATION;

            send();

        } else if(chatButtonEntity.getType().equals(Constant.TAG_PROPERTY)){
            property_id = chatButtonEntity.getId();
        }



        Log.i("z- onClick", new Gson().toJson(chatButtonEntity));
    }

    @Override
    public void showOperationData(OperationResponse operationResponse) {
        response_type_id = operationResponse.getMessage().getResponse_type_id();

        if(!operationResponse.getMessage().getResponse_1().isEmpty()){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setType(Constant.TAG_RECEIPT);
            messageEntity.setMessage(operationResponse.getMessage().getResponse_1());

            objectList.add(messageEntity);
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
        }

        if(!operationResponse.getMessage().getResponse_2().isEmpty()){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setType(Constant.TAG_RECEIPT);
            messageEntity.setMessage(operationResponse.getMessage().getResponse_2());
            objectList.add(messageEntity);
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
        }

        List<ChatButtonEntity> chatButtonEntityList = new ArrayList<>();

        for(OperationResponse.OperationBean operationBean
                : operationResponse.getOperation()){
            chatButtonEntityList.add(new ChatButtonEntity(Constant.TAG_OPERATION,
                    operationBean.getOperation_id(),
                    operationBean.getOperation_description()));
        }

        objectList.addAll(chatButtonEntityList);
        chatAdapter.addData(objectList);
        chatAdapter.notifyItemInserted(objectList.size()-1);


    }

    @Override
    public void showPropertyData(ChatResponse.PropertyBean propertyResponse) {

    }

    @Override
    public void showPropertyTypeData(ChatResponse.PropertyTypeBean propertyTypeResponse) {

    }

    @Override
    public void showMessageError(String error) {
        Snackbar.make(clGeneral, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void send() {
        Map<String, String> data = new HashMap<>();
        data.put("message", message);
        data.put("id", id);
        data.put("operation_id", operation_id);
        data.put("property_id", property_id);

        chatPresenter.sendMessage(data);
    }

    @Override
    public void clear() {
        id = "";
        operation_id = "";
        property_id = "";
    }

    @OnClick(R.id.imgSend)
    public void onClickSend(){
        message = txtMessage.getText().toString().trim();
        if(chatPresenter.validMessage(message)){

            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setType(Constant.TAG_SEND);
            messageEntity.setMessage(message);
            objectList.add(messageEntity);
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);

            send();
        }

    }

}
