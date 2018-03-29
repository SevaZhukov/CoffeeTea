package com.mrswimmer.coffeetea.presentation.main.fragment.catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;

import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.Product;
import com.mrswimmer.coffeetea.presentation.main.fragment.catalog.recycler.ProductsAdapter;
import com.mrswimmer.coffeetea.presentation.main.fragment.filter.FilterFragmentPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CatalogFragment extends MvpAppCompatFragment implements CatalogFragmentView {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_catalog, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        presenter.setProductsForRecycler();
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
                //presenter.gotoProd();
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
