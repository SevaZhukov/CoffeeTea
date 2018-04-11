package com.mrswimmer.coffeetea.presentation.main.fragment.review;

import com.mrswimmer.coffeetea.presentation.base.BaseView;
import com.mrswimmer.coffeetea.data.model.Review;

import java.util.ArrayList;

interface ReviewFragmentView extends BaseView {
    void initAdapter(ArrayList<Review> reviews);

    void setTextOnButton(String message);
}
