package com.mrswimmer.coffeetea.presentation.main.fragment.shop.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    private Context context;

    @Inject
    @Local
    Router localRouter;

    public ShopAdapter(ArrayList<Shop> shops, Context context) {
        this.shops = shops;
        this.context = context;
        App.getComponent().inject(this);
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ShopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.username.setText(review.getUsername());
        holder.description.setText(review.getDescription());
        holder.ratingBar.setRating(review.getMark());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
