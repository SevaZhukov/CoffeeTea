package com.mrswimmer.coffeetea.presentation.main.fragment.shop.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mrswimmer.coffeetea.R;

public class ShopViewHolder extends RecyclerView.ViewHolder {

    TextView adress;
    TextView city;
    TextView hours;
    TextView reviews;
    RatingBar ratingBar;

    public ShopViewHolder(View v) {
        super(v);
        adress = v.findViewById(R.id.item_shop_adress);
        city = v.findViewById(R.id.item_shop_city);
        hours = v.findViewById(R.id.item_shop_hours_work);
        reviews = v.findViewById(R.id.item_shop_reviews);
        ratingBar = v.findViewById(R.id.item_shop_rate);
    }
}
