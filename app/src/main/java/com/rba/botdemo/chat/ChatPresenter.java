package com.rba.botdemo.chat;

import com.rba.botdemo.api.NetworkError;
import com.rba.botdemo.model.response.ChatResponse;
import com.rba.botdemo.model.response.ErrorResponse;
import com.rba.botdemo.model.response.OperationResponse;
import com.rba.botdemo.util.Util;

import java.util.Map;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class ChatPresenter {

    private ChatView chatView;
    private ChatInteractor chatInteractor;
    private CompositeSubscription subscription;

    public ChatPresenter(ChatInteractor chatInteractor, ChatView chatView){
        this.chatInteractor = chatInteractor;
        this.chatView = chatView;
    }

    public void sendMessage(Map<String, String> data) {
        this.subscription = new CompositeSubscription();

        Subscription subscription = chatInteractor.sendMessage(data, new ChatCallback() {

            @Override
            public void onChatOperationResponse(OperationResponse operationResponse) {
                chatView.showOperationData(operationResponse);
            }

            @Override
            public void onChatPropertyResponse(ChatResponse.PropertyBean propertyResponse) {
                chatView.showPropertyData(propertyResponse);
            }

            @Override
            public void onChatPropertyTypeResponse(ChatResponse.PropertyTypeBean propertyTypeResponse) {
                chatView.showPropertyTypeData(propertyTypeResponse);
            }

            @Override
            public void onChatError(ErrorResponse errorResponse) {
                chatView.showMessageError(errorResponse.get_meta().getStatus());
            }

            @Override
            public void onChatFailure(NetworkError networkError) {
                chatView.showMessageError(networkError.getMessage());
            }
        });

        this.subscription.add(subscription);
    }


    public void cancel() {
        subscription.unsubscribe();
    }

    public boolean validMessage(String message){
        return Util.validMessage(message);
    }


}
