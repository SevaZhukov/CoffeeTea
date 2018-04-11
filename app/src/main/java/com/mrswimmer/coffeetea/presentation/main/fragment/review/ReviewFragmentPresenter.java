package com.mrswimmer.coffeetea.presentation.main.fragment.review;

import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class ReviewFragmentPresenter extends MvpPresenter<ReviewFragmentView> {
    ArrayList<Review> array = new ArrayList<>();
    @Inject
    FireService fireService;
    @Inject
    SharedPreferences settings;
    public static String id;
    public static boolean shop;

    public ReviewFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setReviewsForRecycler(String id, boolean shop) {
        this.id = id;
        this.shop = shop;
        fireService.getReviews(id, shop, new FireService.ReviewsCallback() {
            @Override
            public void onSuccess(List<Review> reviews) {
                Log.i("code", "reviews size " + reviews.size());
                array.clear();
                for (int i = 0; i < reviews.size(); i++) {
                    array.add(reviews.get(i));
                    if (reviews.get(i).getUserId().equals(settings.getString(Settings.USER_ID, "0"))) {
                        getViewState().setTextOnButton("Изменить отзыв");
                    }
                }
                getViewState().initAdapter(array);
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showToast(e.getMessage());
            }
        });
    }
}
