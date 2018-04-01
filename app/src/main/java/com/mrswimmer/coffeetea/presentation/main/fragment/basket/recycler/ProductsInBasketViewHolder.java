package com.mrswimmer.coffeetea.presentation.main.fragment.basket.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mrswimmer.coffeetea.R;

public class ProductsInBasketViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView city;
    TextView adress;
    TextView count;
    TextView cost;
    TextView newCost;
    ImageView delete;
    ImageView image;
    ImageView up;
    ImageView down;
    RatingBar ratingBar;
    public ProductsInBasketViewHolder(View v) {
        super(v);
        name = v.findViewById(R.id.item_basket_name);
        image = v.findViewById(R.id.item_basket_image);
        cost = v.findViewById(R.id.item_basket_cost);
        newCost = v.findViewById(R.id.item_basket_cost_sale);
        ratingBar = v.findViewById(R.id.item_basket_rate);
        city = v.findViewById(R.id.item_basket_city);
        adress = v.findViewById(R.id.item_basket_adress);
        count = v.findViewById(R.id.item_basket_count);
        up = v.findViewById(R.id.item_basket_up);
        down = v.findViewById(R.id.item_basket_down);
        delete = v.findViewById(R.id.item_basket_delete);

    }
}
