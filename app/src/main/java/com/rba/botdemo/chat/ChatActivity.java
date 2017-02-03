package com.rba.botdemo.chat;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.rba.botdemo.R;
import com.rba.botdemo.base.BaseActivity;
import com.rba.botdemo.component.chatbutton.ChatButton;
import com.rba.botdemo.component.chatbutton.ChatButtonOnClick;
import com.rba.botdemo.model.entity.ChatButtonEntity;
import com.rba.botdemo.model.response.SynchronizeResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends BaseActivity implements ChatButtonOnClick {

    @BindView(R.id.chbGeneral) ChatButton chbGeneral;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        load();
    }

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

    @Override
    public void onChatButtonClick(ChatButtonEntity chatButtonEntity) {
        Toast.makeText(this, chatButtonEntity.getDescription(), Toast.LENGTH_SHORT).show();
    }
}
