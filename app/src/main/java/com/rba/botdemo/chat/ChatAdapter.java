package com.rba.botdemo.chat;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rba.botdemo.R;
import com.rba.botdemo.component.chatbutton.ChatButton;
import com.rba.botdemo.component.chatbutton.ChatButtonOnClick;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_chatbutton, parent, false);
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
        }else if(objectList.get(position) instanceof  ChatButtonEntity){
            ButtonViewHolder buttonViewHolder = (ButtonViewHolder) holder;
            ChatButtonEntity chatButtonEntity = (ChatButtonEntity) objectList.get(position);
            buttonViewHolder.chbGeneral.addChatButton(chatButtonEntity);
        }

        /*
        if (objectList.get(position) instanceof OfferJobResponse.DataBean) {

            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            OfferJobResponse.DataBean offerJobDataBean = ((OfferJobResponse.DataBean)
                    objectList.get(position));
            itemHolder.lblCompany.setText(offerJobDataBean.getCompany());
            Picasso.with(searchFragment.getContext())
                    .load(offerJobDataBean.getImage())
                    .error(R.drawable.ic_default_aptitus)
                    .placeholder(R.drawable.ic_default_aptitus)
                    .error(R.drawable.ic_default_aptitus)
                    .into(itemHolder.imgCompany);

            itemHolder.lblTitle.setText(offerJobDataBean.getTitle());
            itemHolder.lblAddress.setText(offerJobDataBean.getLocation());


            if(offerJobDataBean.getPublished_at_ago().length() < 5){
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        Util.dipToPx(searchFragment.getContext(), 60),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                itemHolder.lblTime.setLayoutParams(layoutParams);
                itemHolder.lblTime.setGravity(Gravity.CENTER);
            }else{
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                itemHolder.lblTime.setLayoutParams(layoutParams);
                itemHolder.lblTime.setGravity(Gravity.END);
            }

            itemHolder.lblTime.setText(offerJobDataBean.getPublished_at_ago());

            itemHolder.lblArea.setText(offerJobDataBean.getArea());

            if(offerJobDataBean.isApplied()){
                itemHolder.lnlApplied.setVisibility(View.VISIBLE);
            }else{
                itemHolder.lnlApplied.setVisibility(View.GONE);
            }

            if (offerJobDataBean.isFavorite()) {
                itemHolder.lbvFavorite.setImageResource(R.drawable.ic_star_blue);
            } else {
                itemHolder.lbvFavorite.setImageResource(R.drawable.ic_star_border_grey);
            }

            if (offerJobDataBean.isFeatured()) {
                itemHolder.imgBookMark.setVisibility(View.VISIBLE);

                //itemHolder.frmGeneral.setBackgroundColor(ContextCompat.getColor(searchFragment.getContext(), R.color.background_outstanding));
            } else {
                itemHolder.imgBookMark.setVisibility(View.GONE);

                //itemHolder.frmGeneral.setBackgroundColor(ContextCompat.getColor(searchFragment.getContext(), R.color.white));
            }


        } else if (objectList.get(position) instanceof ErrorEntity) {
            ((ErrorViewHolder) holder).lblError.setText(((ErrorEntity)
                    objectList.get(position)).getDescription());

        } else if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).pbLoading.setIndeterminate(true);
        }
        */
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }


    /*
    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgCompany;
        private final LikeButtonViewBlue lbvFavorite;
        private final TextView lblTitle;
        private final TextView lblCompany;
        private final TextView lblAddress;
        private final TextView lblTime;
        private final TextView lblArea;
        private final FrameLayout frmGeneral;
        private final AppCompatImageView imgBookMark;
        private final LinearLayout lnlApplied;

        ItemViewHolder(View view) {
            super(view);
            lblTitle = (TextView) view.findViewById(R.id.lblTitle);
            lblCompany = (TextView) view.findViewById(R.id.lblCompany);
            lblAddress = (TextView) view.findViewById(R.id.lblAddress);
            lblTime = (TextView) view.findViewById(R.id.lblTime);
            lblArea = (TextView) view.findViewById(R.id.lblArea);
            imgCompany = (ImageView) view.findViewById(R.id.imgCompany);
            lbvFavorite = (LikeButtonViewBlue) view.findViewById(R.id.lbvFavorite);
            frmGeneral = (FrameLayout) view.findViewById(R.id.frmGeneral);
            imgBookMark = (AppCompatImageView) view.findViewById(R.id.imgBookMark);
            lnlApplied = (LinearLayout) view.findViewById(R.id.lnlApplied);

            frmGeneral.setOnClickListener(this);
            lbvFavorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.lbvFavorite:
                    if(userDB.isSession()){
                        lbvFavorite.setItemPosition(getAdapterPosition());
                        if(((OfferJobResponse.DataBean) objectList.get(getAdapterPosition())).isFavorite()){
                            lbvFavorite.startUnLikeAnimation();
                        }else{
                            lbvFavorite.startLikeAnimation();
                        }
                    }else{
                        searchFragment.goToLoginActivity();
                    }

                    break;
                case R.id.frmGeneral:
                    searchFragment.goToDetailActivity(getAdapterPosition());
                    break;
            }
        }

    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        final ProgressBar pbLoading;

        LoadingViewHolder(View v) {
            super(v);
            pbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);
        }
    }
    */

    private class MessageViewHolder extends RecyclerView.ViewHolder {

        final AppCompatTextView lblMessage;

        MessageViewHolder(View itemView) {
            super(itemView);
            lblMessage = (AppCompatTextView) itemView.findViewById(R.id.lblMessage);
        }

    }

    private class ButtonViewHolder extends RecyclerView.ViewHolder implements ChatButtonOnClick {

        final ChatButton chbGeneral;

        ButtonViewHolder(View itemView) {
            super(itemView);
            chbGeneral = (ChatButton) itemView.findViewById(R.id.chbGeneral);
            chbGeneral.setOnChatButtonClickListener(this);
        }

        @Override
        public void onChatButtonClick(ChatButtonEntity chatButtonEntity) {
            chatView.onClickChatButton(chatButtonEntity);
        }
    }

}