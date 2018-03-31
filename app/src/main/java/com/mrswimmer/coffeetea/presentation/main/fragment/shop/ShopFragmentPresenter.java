package com.mrswimmer.coffeetea.presentation.main.fragment.shop;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class ShopFragmentPresenter extends MvpPresenter<ShopFragmentView> {

    @Inject
    FireService fireService;

    public ShopFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setReviewsForRecycler(String id) {
        fireService.getReviews(id, new FireService.ReviewsCallback() {
            @Override
            public void onSuccess(List<Review> reviews) {
                Log.i("code", "reviews size " + reviews.size());
                ArrayList<Review> array = new ArrayList<>();
                for(int i=0; i<reviews.size(); i++) {
                    array.add(reviews.get(i));
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
