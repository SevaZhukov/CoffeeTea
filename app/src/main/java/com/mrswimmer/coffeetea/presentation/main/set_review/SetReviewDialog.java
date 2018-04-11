package com.mrswimmer.coffeetea.presentation.main.set_review;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.domain.service.FireService;
import com.mrswimmer.coffeetea.presentation.main.fragment.review.ReviewFragmentPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetReviewDialog extends AppCompatActivity {
    @Inject
    FireService fireService;
    @Inject
    SharedPreferences settings;
    @BindView(R.id.dialog_review_description)
    EditText description;
    @BindView(R.id.dialog_review_rate)
    RatingBar rate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_review);
        ButterKnife.bind(this);
        App.getComponent().inject(this);
    }

    @OnClick(R.id.dialog_review_next)
    void onNextClick() {
        Review review = new Review(description.getText().toString(), (int) rate.getRating(), settings.getString(Settings.USER_ID, "0"), settings.getString(Settings.USERNAME, "0"));
        fireService.setReview(ReviewFragmentPresenter.id, review);
        finish();
    }

    @OnClick(R.id.dialog_review_back)
    void onBackClick() {
        finish();
    }
}

