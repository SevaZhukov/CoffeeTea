package com.mrswimmer.coffeetea.presentation.main.fragment.review;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;

@InjectViewState
public class ReviewFragmentPresenter extends MvpPresenter<ReviewFragmentView> {

    public ReviewFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
