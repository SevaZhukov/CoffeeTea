package com.mrswimmer.coffeetea.presentation.main.fragment.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.Order;
import com.mrswimmer.coffeetea.presentation.base.BaseFragment;
import com.mrswimmer.coffeetea.presentation.main.fragment.order.recycler.OrderAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class OrderFragment extends BaseFragment implements OrderFragmentView {
    @InjectPresenter
    OrderFragmentPresenter presenter;

    @BindView(R.id.order_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.order_empty_text)
    TextView emptyText;

    @ProvidePresenter
    public OrderFragmentPresenter presenter() {
        return new OrderFragmentPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        presenter.setRecycler();
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_order;
    }

    @Override
    public void initAdapter(ArrayList<Order> orders) {
        recyclerView.setAdapter(new OrderAdapter(orders));
    }

    @Override
    public void setEmptyText() {
        emptyText.setVisibility(View.VISIBLE);
    }
}
