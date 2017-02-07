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
    public void onClickChatButton(ChatButtonEntity.ChatButtonBean chatButtonBean) {

        if(chatButtonBean.getType().equals(Constant.TAG_OPERATION)){
            id = chatButtonBean.getId();
            message = Constant.TAG_OPERATION;
            send();
        } else if(chatButtonBean.getType().equals(Constant.TAG_PROPERTY_TYPE)){
            message = Constant.TAG_PROPERTY;
            operation_id = id;
            id = chatButtonBean.getId();
            property_id = chatButtonBean.getId();
            send();
        } else if(chatButtonBean.getType().equals(Constant.TAG_PROPERTY)){
            property_id = chatButtonBean.getId();
        }

        Log.i("z- onClick", new Gson().toJson(chatButtonBean));
    }

    @Override
    public void showOperationData(ChatResponse chatResponse) {
        response_type_id = chatResponse.getMessage().getResponse_type_id();

        if(!chatResponse.getMessage().getResponse_1().isEmpty()){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setType(Constant.TAG_RECEIPT);
            messageEntity.setMessage(chatResponse.getMessage().getResponse_1());

            objectList.add(messageEntity);
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
        }

        if(!chatResponse.getMessage().getResponse_2().isEmpty()){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setType(Constant.TAG_RECEIPT);
            messageEntity.setMessage(chatResponse.getMessage().getResponse_2());
            objectList.add(messageEntity);
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
        }

        List<ChatButtonEntity.ChatButtonBean> chatButtonEntityList = new ArrayList<>();

        for(ChatResponse.OperationBean operationBean
                : chatResponse.getOperation()){
            ChatButtonEntity.ChatButtonBean chatButtonBean = new ChatButtonEntity.ChatButtonBean();
            chatButtonBean.setId(Constant.TAG_OPERATION);
            chatButtonBean.setType(operationBean.getOperation_id());
            chatButtonBean.setDescription(operationBean.getOperation_description());
            chatButtonEntityList.add(chatButtonBean);
        }
        ChatButtonEntity chatButtonEntity = new ChatButtonEntity();
        chatButtonEntity.setChatButtonBeen(chatButtonEntityList);
        objectList.add(chatButtonEntity);

        chatAdapter.addData(objectList);
        chatAdapter.notifyItemInserted(objectList.size()-1);


    }

    @Override
    public void showPropertyData(ChatResponse chatResponse) {
        Log.i("z- showPropertyData", new Gson().toJson(chatResponse));



    }

    @Override
    public void showPropertyTypeData(ChatResponse chatResponse) {
        Log.i("z- showPropertyTypeData", new Gson().toJson(chatResponse));

        if(!chatResponse.getMessage().getResponse_1().isEmpty()){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setType(Constant.TAG_RECEIPT);
            messageEntity.setMessage(chatResponse.getMessage().getResponse_1());

            objectList.add(messageEntity);
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
        }

        if(!chatResponse.getMessage().getResponse_2().isEmpty()){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setType(Constant.TAG_RECEIPT);
            messageEntity.setMessage(chatResponse.getMessage().getResponse_2());
            objectList.add(messageEntity);
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
        }

        List<ChatButtonEntity.ChatButtonBean> chatButtonEntityList = new ArrayList<>();

        for(ChatResponse.PropertyTypeBean propertyTypeBean
                : chatResponse.getProperty_type()){
            ChatButtonEntity.ChatButtonBean chatButtonBean = new ChatButtonEntity.ChatButtonBean();
            chatButtonBean.setId(Constant.TAG_PROPERTY_TYPE);
            chatButtonBean.setType(propertyTypeBean.getProperty_id());
            chatButtonBean.setDescription(propertyTypeBean.getProperty_description());
            chatButtonEntityList.add(chatButtonBean);
        }
        ChatButtonEntity chatButtonEntity = new ChatButtonEntity();
        chatButtonEntity.setChatButtonBeen(chatButtonEntityList);
        objectList.add(chatButtonEntity);

        chatAdapter.addData(objectList);
        chatAdapter.notifyItemInserted(objectList.size()-1);
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
