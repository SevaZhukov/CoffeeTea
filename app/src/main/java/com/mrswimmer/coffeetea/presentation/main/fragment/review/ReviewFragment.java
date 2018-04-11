package com.mrswimmer.coffeetea.presentation.main.fragment.review;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.presentation.base.BaseFragment;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.presentation.main.fragment.review.recycler.ReviewAdapter;
import com.mrswimmer.coffeetea.presentation.main.set_review.SetReviewDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ReviewFragment extends BaseFragment implements ReviewFragmentView {
    @InjectPresenter
    ReviewFragmentPresenter presenter;

    @ProvidePresenter
    public ReviewFragmentPresenter presenter() {
        return new ReviewFragmentPresenter();
    }

    @BindView(R.id.review_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.review_set_review)
    Button setReview;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = this.getArguments();
        presenter.setReviewsForRecycler(bundle.getString("id"), bundle.getBoolean("shop", false));
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_review;
    }

    @OnClick(R.id.review_set_review)
    void onSetReviewClick() {
        Intent intent = new Intent(getActivity(), SetReviewDialog.class);
        startActivity(intent);
    }

    @Override
    public void initAdapter(ArrayList<Review> reviews) {
        recyclerView.setAdapter(new ReviewAdapter(reviews));
    }

    @Override
    public void setTextOnButton(String message) {
        setReview.setText(message);
    }
}
