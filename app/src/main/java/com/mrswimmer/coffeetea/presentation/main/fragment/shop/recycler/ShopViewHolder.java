package com.mrswimmer.coffeetea.presentation.main.fragment.shop.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mrswimmer.coffeetea.R;

public class ShopViewHolder extends RecyclerView.ViewHolder {

    TextView username;
    TextView description;
    RatingBar ratingBar;
    public ShopViewHolder(View v) {
        super(v);
        ratingBar = v.findViewById(R.id.item_review_rate);
        username = v.findViewById(R.id.item_review_name);
        description = v.findViewById(R.id.item_review_description);
    }
}
