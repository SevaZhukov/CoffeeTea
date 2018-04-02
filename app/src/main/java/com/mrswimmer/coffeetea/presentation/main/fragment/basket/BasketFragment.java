package com.mrswimmer.coffeetea.presentation.main.fragment.basket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.presentation.base.BaseFragment;
import com.mrswimmer.coffeetea.presentation.main.fragment.basket.recycler.ProductsInBasketAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class BasketFragment extends BaseFragment implements BasketFragmentView {
    @InjectPresenter
    BasketFragmentPresenter presenter;

    @BindView(R.id.basket_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.basket_bottom)
    ConstraintLayout bottomLayout;
    @BindView(R.id.basket_bottom_sum)
    TextView sumText;
    @BindView(R.id.basket_empty_text)
    TextView emptyText;

    @ProvidePresenter
    public BasketFragmentPresenter presenter() {
        return new BasketFragmentPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            String orderId = bundle.getString("id");
            presenter.setOrderRecycler(orderId);
        } else {
            presenter.setRecycler();
        }
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_basket;
    }

    @Override
    public void initAdapter(ArrayList<ProductInBasket> products, boolean orders) {
        recyclerView.setAdapter(new ProductsInBasketAdapter(products, getActivity(), orders));
    }

    @OnClick(R.id.basket_bottom_to_order)
    void onToOrderClick() {
        presenter.toOrder();
    }
    @Override
    public void setBottomVisible(boolean visible) {
        if (visible)
            bottomLayout.setVisibility(View.VISIBLE);
        else
            bottomLayout.setVisibility(View.GONE);
    }

    @Override
    public void setSum(int sum) {
        sumText.setText("Сумма: " + sum + " руб");
    }

    @Override
    public void setEmptyText() {
        emptyText.setVisibility(View.VISIBLE);
    }
}
