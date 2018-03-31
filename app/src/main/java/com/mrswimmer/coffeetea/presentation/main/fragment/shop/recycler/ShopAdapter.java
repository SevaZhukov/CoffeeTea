package com.mrswimmer.coffeetea.presentation.main.fragment.shop.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.data.model.Shop;
import com.mrswimmer.coffeetea.di.qualifier.Local;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class ShopAdapter extends RecyclerView.Adapter<ShopViewHolder> {
    private ArrayList<Shop> shops = new ArrayList<>();

    @Inject
    @Local
    Router localRouter;

    public ShopAdapter(ArrayList<Shop> shops) {
        this.shops = shops;
        App.getComponent().inject(this);
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop, parent, false);
        return new ShopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.adress.setText(shop.getAdress());
        holder.city.setText(shop.getCity());
        holder.hours.setText(shop.getHours());
        SpannableString content = new SpannableString(shop.getReviewsString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        holder.reviews.setText(content);
        holder.ratingBar.setRating(shop.getRate());

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }
}