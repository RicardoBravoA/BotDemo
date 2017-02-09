package com.rba.botdemo.chat;

import android.content.Intent;
import android.net.Uri;
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
import com.rba.botdemo.model.entity.PropertyEntity;
import com.rba.botdemo.model.response.ChatResponse;
import com.rba.botdemo.model.response.PropertyBean;
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
    private String operation = "";
    private String propertyType = "";
    private List<ChatButtonEntity.ChatButtonBean> chatButtonEntityList = new ArrayList<>();

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

        Log.i("z- onClick", new Gson().toJson(chatButtonBean));

        if(chatButtonBean.getType().equals(Constant.TAG_OPERATION)){

            operation = chatButtonBean.getId();
            message = "";
            Log.i("z- operation", ""+operation+" - "+message);

            removeItem(objectList.size()-1);
            showMessagerUser(getString(R.string.user_message_operation, operation));
            chatButtonEntityList.clear();
            send();

        } else if(chatButtonBean.getType().equals(Constant.TAG_PROPERTY_TYPE)){

            message = "";
            propertyType = chatButtonBean.getId();

            Log.i("z- propertyType", ""+propertyType+" - "+message+" - "+operation);

            removeItem(objectList.size()-1);
            showMessagerUser(propertyType);
            chatButtonEntityList.clear();

            send();

        }

    }

    @Override
    public void showOperationData(ChatResponse chatResponse) {

        showResult(chatResponse, Constant.TAG_OPERATION_BUTTON);

    }

    @Override
    public void showPropertyData(ChatResponse chatResponse) {
        Log.i("z- showPropertyData", new Gson().toJson(chatResponse));

        showResult(chatResponse, Constant.TAG_SHOW_PROPERTY);

    }

    @Override
    public void showPropertyTypeData(ChatResponse chatResponse) {
        Log.i("z- showPropertyTypeData", new Gson().toJson(chatResponse));

        showResult(chatResponse, Constant.TAG_PROPERTY_TYPE_BUTTON);

    }

    @Override
    public void showMessageError(String error) {
        Snackbar.make(clGeneral, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMessagerUser(String message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setType(Constant.TAG_SEND);
        messageEntity.setMessage(message);
        objectList.add(messageEntity);
        chatAdapter.addData(objectList);
        chatAdapter.notifyItemInserted(objectList.size()-1);
    }

    @Override
    public void removeItem(int position) {
        Log.i("x- remove", new Gson().toJson(objectList.get(position)));
        objectList.remove(position);
        chatAdapter.notifyItemRemoved(position);
        rcvChat.smoothScrollToPosition(position);
    }

    @Override
    public void send() {
        Map<String, String> data = new HashMap<>();
        data.put("message", message);
        data.put("operation", operation);
        data.put("property_type", propertyType);

        chatPresenter.sendMessage(data);
    }

    @Override
    public void onClickProperty(PropertyBean propertyBean) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(propertyBean.getUrl())));
    }

    @Override
    public void showResult(ChatResponse chatResponse, int value) {

        Log.i("x- chatResponse", new Gson().toJson(chatResponse));

        if(!chatResponse.getMessage().getResponse_1().isEmpty()){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setType(Constant.TAG_RECEIPT);
            messageEntity.setMessage(chatResponse.getMessage().getResponse_1());

            objectList.add(messageEntity);
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
            rcvChat.smoothScrollToPosition(objectList.size()-1);
        }

        if(!chatResponse.getMessage().getResponse_2().isEmpty()){
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setType(Constant.TAG_RECEIPT);
            messageEntity.setMessage(chatResponse.getMessage().getResponse_2());
            objectList.add(messageEntity);
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
            rcvChat.smoothScrollToPosition(objectList.size()-1);
        }


        if(value == Constant.TAG_OPERATION_BUTTON){

            for(ChatResponse.OperationBean operationBean
                    : chatResponse.getOperation()){
                ChatButtonEntity.ChatButtonBean chatButtonBean = new ChatButtonEntity.ChatButtonBean();
                chatButtonBean.setId(operationBean.getOperation());
                chatButtonBean.setType(Constant.TAG_OPERATION);
                chatButtonEntityList.add(chatButtonBean);
            }
            ChatButtonEntity chatButtonEntity = new ChatButtonEntity();
            chatButtonEntity.setChatButtonBeen(chatButtonEntityList);
            objectList.add(chatButtonEntity);
            Log.i("x- objectList btn", new Gson().toJson(objectList));
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
            rcvChat.smoothScrollToPosition(objectList.size()-1);

        }else if(value == Constant.TAG_PROPERTY_TYPE_BUTTON){

            for(ChatResponse.PropertyTypeBean propertyTypeBean
                    : chatResponse.getProperty_type()){
                ChatButtonEntity.ChatButtonBean chatButtonBean = new ChatButtonEntity.ChatButtonBean();
                chatButtonBean.setId(propertyTypeBean.getProperty_type());
                chatButtonBean.setType(Constant.TAG_PROPERTY_TYPE);
                chatButtonEntityList.add(chatButtonBean);
            }
            ChatButtonEntity chatButtonEntity = new ChatButtonEntity();
            chatButtonEntity.setChatButtonBeen(chatButtonEntityList);
            objectList.add(chatButtonEntity);
            Log.i("x- objectList btn", new Gson().toJson(objectList));
            chatAdapter.addData(objectList);
            chatAdapter.notifyItemInserted(objectList.size()-1);
            rcvChat.smoothScrollToPosition(objectList.size()-1);

        }else if(value == Constant.TAG_SHOW_PROPERTY){

            PropertyEntity propertyEntity = new PropertyEntity();
            propertyEntity.setProperty(chatResponse.getProperty());
            objectList.add(propertyEntity);
            chatAdapter.notifyItemInserted(objectList.size()-1);
            rcvChat.smoothScrollToPosition(objectList.size()-1);

        }


    }

    @Override
    public void clear() {
        message = "";
        operation = "";
        propertyType = "";
        txtMessage.setText("");
        chatButtonEntityList.clear();
    }

    @OnClick(R.id.imgSend)
    public void onClickSend(){
        message = txtMessage.getText().toString().trim();

        operation = "";
        propertyType = "";
        chatButtonEntityList.clear();

        if(chatPresenter.validMessage(message)){

            showMessagerUser(message);

            send();
            txtMessage.setText("");
        }

    }

}
