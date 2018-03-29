package com.mrswimmer.coffeetea.presentation.main.fragment.poduct;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;

import butterknife.ButterKnife;

public class ProductFragment extends MvpAppCompatFragment implements ProductFragmentView {
    @InjectPresenter
    ProductFragmentPresenter presenter;

    @ProvidePresenter
    public ProductFragmentPresenter presenter() {
        return new ProductFragmentPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Bundle bundle = this.getArguments();
        Log.i("code", "prod " + bundle.getString("id"));
    }
}
