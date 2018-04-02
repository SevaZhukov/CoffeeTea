package com.mrswimmer.coffeetea.presentation.main.fragment.order.recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.model.Order;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private ArrayList<Order> orders = new ArrayList<>();

    @Inject
    @Local
    Router localRouter;
    @Inject
    FireService fireService;
    @Inject
    SharedPreferences settings;

    public OrderAdapter(ArrayList<Order> orders) {
        this.orders = orders;
        App.getComponent().inject(this);
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.id.setText(order.getId());
        holder.cost.setText(order.getSum()+"");
        holder.date.setText(order.getDateString());
        holder.delete.setOnClickListener(v -> deleteOrder(order));
        holder.show.setOnClickListener(v -> showOrder(order));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    void deleteOrder(Order order) {

    }

    void showOrder(Order order) {
        localRouter.navigateTo(Screens.BASKET_OF_ORDER_SCREEN, order.getId());
    }
}
