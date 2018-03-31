package com.mrswimmer.coffeetea.presentation.main.fragment.review.recycler;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.data.model.product.Product;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private ArrayList<Review> reviews = new ArrayList<>();

    @Inject
    @Local
    Router localRouter;

    public ReviewAdapter(ArrayList<Review> reviews) {
        this.reviews = reviews;
        App.getComponent().inject(this);
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
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
