package com.rba.botdemo.chat;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.rba.botdemo.R;
import com.rba.botdemo.component.chatbutton.ChatButtonOnClick;
import com.rba.botdemo.component.chatbutton.ChatRadioGroup;
import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.model.entity.MessageEntity;
import com.rba.botdemo.model.entity.PropertyEntity;
import com.rba.botdemo.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> objectList;
    private Context context;
    private ChatView chatView;

    public ChatAdapter(Context context, ChatView chatView) {
        this.objectList = new ArrayList<>();
        this.context = context;
        this.chatView = chatView;
    }

    @Override
    public int getItemViewType(int position) {
        if (objectList.get(position) instanceof MessageEntity) {
            if(((MessageEntity) objectList.get(position)).getType() == 0){
                return Constant.TAG_MESSAGE_RIGHT;
            }else{
                return Constant.TAG_MESSAGE_LETF;
            }

        }else if(objectList.get(position) instanceof ChatButtonEntity){
            return Constant.TAG_BUTTON;
        }else if(objectList.get(position) instanceof PropertyEntity){
            return Constant.TAG_SHOW_PROPERTY;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if(viewType == Constant.TAG_MESSAGE_LETF){
            view = LayoutInflater.from(context).inflate(R.layout.item_message_left, parent, false);
            return new MessageViewHolder(view);
        }else if(viewType == Constant.TAG_MESSAGE_RIGHT){
            view = LayoutInflater.from(context).inflate(R.layout.item_message_right, parent, false);
            return new MessageViewHolder(view);
        } else if(viewType == Constant.TAG_BUTTON){
            view = LayoutInflater.from(context).inflate(R.layout.item_chat_radio_group, parent, false);
            return new ButtonViewHolder(view);
        } else if(viewType == Constant.TAG_SHOW_PROPERTY){
            view = LayoutInflater.from(context).inflate(R.layout.content_property, parent, false);
            return new PropertyViewHolder(view);
        }

        return null;
    }

    public void addData(List<Object> objectList){
        this.objectList = objectList;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(objectList.get(position) instanceof MessageEntity){
            MessageViewHolder messageViewHolder = (MessageViewHolder) holder;
            MessageEntity messageEntity = (MessageEntity) objectList.get(position);
            messageViewHolder.lblMessage.setText(messageEntity.getMessage());
            Log.i("z- MessageEntity", ""+position);
        }else if(objectList.get(position) instanceof  ChatButtonEntity){
            ButtonViewHolder buttonViewHolder = (ButtonViewHolder) holder;
            ChatButtonEntity chatButtonEntity = (ChatButtonEntity) objectList.get(position);
            Log.i("z- chatButtonEntity", new Gson().toJson(chatButtonEntity));
            buttonViewHolder.chbGeneral.addChatButton(chatButtonEntity);
            Log.i("z- ChatButtonEntity", ""+position);
        }else if(objectList.get(position) instanceof PropertyEntity){
            PropertyViewHolder propertyViewHolder = (PropertyViewHolder) holder;
            PropertyEntity propertyEntity = (PropertyEntity) objectList.get(position);
            Log.i("z- propertyEntity", new Gson().toJson(propertyEntity));
            Log.i("z- ChatButtonEntity", ""+position);

            //recycler view aqui

            LinearLayoutManager layoutManager = new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false);
            propertyViewHolder.rcvProperty.setLayoutManager(layoutManager);
            PropertyAdapter propertyAdapter = new PropertyAdapter(context, propertyEntity.getProperty(), chatView);
            propertyViewHolder.rcvProperty.setAdapter(propertyAdapter);
            propertyViewHolder.rcvProperty.setItemAnimator(new DefaultItemAnimator());
            propertyAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }


    private class MessageViewHolder extends RecyclerView.ViewHolder {

        final AppCompatTextView lblMessage;

        MessageViewHolder(View itemView) {
            super(itemView);
            lblMessage = (AppCompatTextView) itemView.findViewById(R.id.lblMessage);
        }

    }

    private class ButtonViewHolder extends RecyclerView.ViewHolder implements ChatButtonOnClick {

        final ChatRadioGroup chbGeneral;

        ButtonViewHolder(View itemView) {
            super(itemView);
            chbGeneral = (ChatRadioGroup) itemView.findViewById(R.id.chbGeneral);
            chbGeneral.setOnChatButtonClickListener(this);
        }

        @Override
        public void onChatButtonClick(ChatButtonEntity.ChatButtonBean chatButtonBean) {
            chatView.onClickChatButton(chatButtonBean);
        }
    }

    private class PropertyViewHolder extends RecyclerView.ViewHolder {

        final RecyclerView rcvProperty;

        PropertyViewHolder(View itemView) {
            super(itemView);
            rcvProperty = (RecyclerView) itemView.findViewById(R.id.rcvProperty);
        }

    }

}