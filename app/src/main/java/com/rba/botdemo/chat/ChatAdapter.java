package com.rba.botdemo.chat;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
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
        }
        /*
        else if (objectList.get(position) instanceof ErrorEntity) {
            return Constant.TAG_ERROR;
        } else if (objectList.get(position) instanceof LoadingEntity) {
            return Constant.TAG_LOADING;
        } else {
            return -1;
        }
        */return 0;
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
        }

        /*
        switch (viewType) {
            case Constant.TAG_ITEM:
                view = LayoutInflater.from(searchFragment.getContext()).inflate(R.layout.item_job_offer, parent, false);
                return new ItemViewHolder(view);
            case Constant.TAG_ERROR:
                view = LayoutInflater.from(searchFragment.getContext()).inflate(R.layout.item_job_error, parent, false);
                return new ErrorViewHolder(view);
            case Constant.TAG_LOADING:
                view = LayoutInflater.from(searchFragment.getContext()).inflate(R.layout.item_job_loading, parent, false);
                return new LoadingViewHolder(view);
            default:
                return null;
        }
        */
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