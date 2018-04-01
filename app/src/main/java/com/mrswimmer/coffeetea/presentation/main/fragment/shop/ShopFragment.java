package com.mrswimmer.coffeetea.presentation.main.fragment.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.base.BaseFragment;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.data.model.Shop;
import com.mrswimmer.coffeetea.data.model.product.Product;
import com.mrswimmer.coffeetea.presentation.main.fragment.shop.recycler.ShopAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class ShopFragment extends BaseFragment implements ShopFragmentView {
    @InjectPresenter
    ShopFragmentPresenter presenter;

    @ProvidePresenter
    public ShopFragmentPresenter presenter() {
        return new ShopFragmentPresenter();
    }

    @BindView(R.id.shop_recycler)
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = this.getArguments();
        boolean choose = bundle.getBoolean("choose");
        Log.i("code", "shop choose " + choose);
        if (choose)
            presenter.chooseShopsForRecycler();
        else
            presenter.setShopsForRecycler();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initAdapter(ArrayList<Shop> shops, boolean choose) {
        recyclerView.setAdapter(new ShopAdapter(shops, choose));
    }
}
