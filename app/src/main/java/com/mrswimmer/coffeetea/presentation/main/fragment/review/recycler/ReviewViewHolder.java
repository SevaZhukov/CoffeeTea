package com.mrswimmer.coffeetea.presentation.main.fragment.review.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mrswimmer.coffeetea.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    TextView username;
    TextView description;
    RatingBar ratingBar;
    public ReviewViewHolder(View v) {
        super(v);
        ratingBar = v.findViewById(R.id.item_review_rate);
        username = v.findViewById(R.id.item_review_name);
        description = v.findViewById(R.id.item_review_description);
    }
}
