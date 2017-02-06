package com.rba.botdemo.chat;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rba.botdemo.R;
import com.rba.botdemo.model.response.PropertyResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ricardo Bravo on 6/02/17.
 */

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {

    private Context context;
    private List<PropertyResponse.PropertyBean> propertyResponseList;
    static LayoutInflater inflater = null;


    public PropertyAdapter(Context context, List<PropertyResponse.PropertyBean> propertyResponseList) {
        if(context!=null){
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.propertyResponseList = propertyResponseList;
        }
    }

    @Override
    public PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_property, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PropertyViewHolder holder, int position) {

        PropertyResponse.PropertyBean propertyResponse = propertyResponseList.get(position);
        holder.lblTitle.setText(propertyResponse.getTitle());
        holder.lblPrice.setText(propertyResponse.getPrice());

        Picasso.with(context)
                .load(propertyResponse.getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .resize(300, 300)
                .into(holder.imgProperty);
    }

    @Override
    public int getItemCount() {
        return propertyResponseList.size();
    }

    class PropertyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgProperty) AppCompatImageView imgProperty;
        @BindView(R.id.lblTitle) AppCompatTextView lblTitle;
        @BindView(R.id.lblPrice) AppCompatTextView lblPrice;

        public PropertyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
