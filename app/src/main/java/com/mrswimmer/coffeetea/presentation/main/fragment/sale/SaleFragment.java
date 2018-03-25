package com.mrswimmer.coffeetea.presentation.main.fragment.sale;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.presentation.main.fragment.catalog.CatalogFragmentPresenter;
import butterknife.ButterKnife;

public class SaleFragment extends MvpAppCompatFragment implements SaleFragmentView {
    @InjectPresenter
    SaleFragmentPresenter presenter;

    @ProvidePresenter
    public SaleFragmentPresenter presenter() {
        return new SaleFragmentPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sale, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }
}
