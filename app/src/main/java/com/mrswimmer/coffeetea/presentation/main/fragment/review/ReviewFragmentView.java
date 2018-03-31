package com.mrswimmer.coffeetea.presentation.main.fragment.review;

import com.arellomobile.mvp.MvpView;
import com.mrswimmer.coffeetea.data.base.BaseView;
import com.mrswimmer.coffeetea.data.model.Review;

import java.util.ArrayList;
import java.util.List;

interface ReviewFragmentView extends BaseView {
    void initAdapter(ArrayList<Review> reviews);
}