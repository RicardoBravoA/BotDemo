package com.rba.botdemo.chat;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rba.botdemo.R;
import com.rba.botdemo.model.response.PropertyBean;
import com.squareup.picasso.Picasso;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {

    private Context context;
    private List<PropertyBean> propertyBeanList;
    static LayoutInflater inflater = null;
    private ChatView chatView;

    public PropertyAdapter(Context context, List<PropertyBean> propertyBeanList, ChatView chatView) {
        this.context = context;
        this.chatView = chatView;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.propertyBeanList = propertyBeanList;
    }

    @Override
    public PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_property, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PropertyViewHolder holder, int position) {

        PropertyBean propertyResponse = propertyBeanList.get(position);
        holder.lblTitle.setText(propertyResponse.getTitle());
        holder.lblPrice.setText(propertyResponse.getMoney_type()+" "+propertyResponse.getPrice());

        Picasso.with(context)
                .load(propertyResponse.getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .resize(300, 300)
                .into(holder.imgProperty);
    }

    @Override
    public int getItemCount() {
        return propertyBeanList.size();
    }

    class PropertyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgProperty) AppCompatImageView imgProperty;
        @BindView(R.id.lblTitle) AppCompatTextView lblTitle;
        @BindView(R.id.lblPrice) AppCompatTextView lblPrice;

        public PropertyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cvProperty)
        public void onClickProperty(){
            chatView.onClickProperty(propertyBeanList.get(getAdapterPosition()));
        }

    }
}
