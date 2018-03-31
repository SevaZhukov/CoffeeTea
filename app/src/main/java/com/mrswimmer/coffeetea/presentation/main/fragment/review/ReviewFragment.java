package com.mrswimmer.coffeetea.presentation.main.fragment.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.base.BaseFragment;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.presentation.main.fragment.catalog.recycler.ProductsAdapter;
import com.mrswimmer.coffeetea.presentation.main.fragment.review.recycler.ReviewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewFragment extends BaseFragment implements ReviewFragmentView {
    @InjectPresenter
    ReviewFragmentPresenter presenter;

    @ProvidePresenter
    public ReviewFragmentPresenter presenter() {
        return new ReviewFragmentPresenter();
    }

    @BindView(R.id.review_recycler)
    RecyclerView recyclerView;

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

    @Override
    public void initAdapter(ArrayList<Review> reviews) {
        recyclerView.setAdapter(new ReviewAdapter(reviews));
    }
}
