package com.mrswimmer.coffeetea.presentation.main.fragment.catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.presentation.base.BaseFragment;
import com.mrswimmer.coffeetea.data.model.Product;
import com.mrswimmer.coffeetea.presentation.main.fragment.catalog.recycler.ProductsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class CatalogFragment extends BaseFragment implements CatalogFragmentView {
    @InjectPresenter
    CatalogFragmentPresenter presenter;

    @ProvidePresenter
    public CatalogFragmentPresenter presenter() {
        return new CatalogFragmentPresenter();
    }

    @BindView(R.id.catalog_drop_filter)
    Button dropButton;
    @BindView(R.id.catalog_recycler)
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = this.getArguments();
        boolean sale = false;
        if (bundle != null) {
            sale = bundle.getBoolean("sale", false);
            Log.i("code", "in catalog salebool " + sale);
        }
        presenter.setProductsForRecycler(sale);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_catalog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.catalog_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_catalog_filters:
                presenter.gotoFilters();
                return true;
            default:
                return true;
        }
    }

    @Override
    public void initAdapter(ArrayList<Product> products) {
        recyclerView.setAdapter(new ProductsAdapter(products, getActivity()));
    }

    @Override
    public void hideDropButton() {
        dropButton.setVisibility(View.GONE);
    }

    @Override
    public void showDropButton() {
        dropButton.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.catalog_drop_filter)
    void dropFilters() {
        hideDropButton();
        presenter.dropFilters();
    }

}
